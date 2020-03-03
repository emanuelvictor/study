package br.org.pti.api.functional.portalcompras.domain.service;

import br.org.pti.api.functional.portalcompras.domain.repository.IFornecedorRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IUsuarioRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.mail.IUsuarioAbstractMailRepository;
import br.org.pti.api.functional.portalcompras.infrastructure.aid.StandaloneBeanValidation;
import br.org.pti.api.functional.portalcompras.application.context.ContextHolder;
import br.org.pti.api.functional.portalcompras.application.context.HTTPContextHolder;
import br.org.pti.api.functional.portalcompras.application.i18n.MessageSourceHolder;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Permissao;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    /**
     *
     */
    final IFornecedorRepository fornecedorRepository;
    /**
     *
     */
    private final PasswordEncoder passwordEncoder;
    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;
    /**
     *
     */
    private final IUsuarioAbstractMailRepository usuarioMailRepository;
    /**
     *
     */
    private final LdapTemplate ldapTemplate;

    /**
     *
     */
    @Value("${ldap.basedn}")
    private String baseDn;

    /**
     * @return código (token) aleatório de segurança com 32 caracteres
     */
    static String gerarCodigo() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @param filter   {String}
     * @param ativo    Boolean
     * @param pageable Pageable
     * @return Page<Usuario></Usuario>
     */
    @Transactional(readOnly = true)
    public Page<Usuario> listByFilters(final String filter, final Boolean ativo, final Pageable pageable) {
        return this.usuarioRepository.listByFilters(filter, ativo, pageable);
    }

    /**
     * @param id long
     * @return Usuario
     */
    @Transactional(readOnly = true)
    public Usuario findById(final long id) {
        final Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));
        usuario.setFornecedor(this.fornecedorRepository.findByUsuarioId(usuario.getId()));
        return usuario;
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    public Usuario save(final Usuario usuario) {

        usuario.setAtivo(true);

        final String codigo = gerarCodigo();
        usuario.setCodigo(codigo);
        usuario.setSenha(this.passwordEncoder.encode(codigo));// TODO WTF??

        final Usuario usuarioSaved = this.usuarioRepository.save(usuario);

        if (this.findByLdapUsername(usuario.getUsername()) == null) {
            final String url = HTTPContextHolder.getServerURL() + "/sistema/#/cadastrar-senha/" + codigo;
            usuarioMailRepository.sendNewUserAccount(usuario, url);
            usuario.setInterno(false);
        } else {
            usuario.setInterno(true);
            final String url = HTTPContextHolder.getServerURL();
            usuarioMailRepository.sendNewAccountCreated(usuario, url);
        }

        return usuarioSaved;
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    public Usuario save(final long id, final Usuario usuario) {

        usuario.setId(id);

        final Usuario authenticatedUser = ContextHolder.getAuthenticatedUser();

        final Usuario usuarioSaved = this.usuarioRepository.findById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", usuario.getId())));

        usuario.setSenha(usuarioSaved.getSenha());

        if (authenticatedUser.getId().equals(usuarioSaved.getId())) {
            usuario.setGrupoAcesso(usuarioSaved.getGrupoAcesso());
        }

        if (this.findByLdapUsername(usuario.getUsername()) == null) {
            usuario.setInterno(false);
        } else {
            usuario.setInterno(true);
        }

        return this.usuarioRepository.saveAndFlush(usuario);
    }

    /**
     * @param id long
     * @return Usuario
     */
    public Usuario updateAtivo(final long id) {

        final Usuario authenticatedUser = ContextHolder.getAuthenticatedUser();

        final Usuario usuarioSaved = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));

        Assert.notNull(usuarioSaved, MessageSourceHolder.getMessage("repository.notFoundById", id));
        Assert.isTrue(!authenticatedUser.getId().equals(usuarioSaved.getId()), MessageSourceHolder.getMessage("security.accessDenied"));

        usuarioSaved.setAtivo(!usuarioSaved.getAtivo());

        return this.usuarioRepository.saveAndFlush(usuarioSaved);
    }

    /**
     * @param id              long
     * @param currentPassword String
     * @param newPassword     String
     */
    public void updatePassword(final long id, final String currentPassword, final String newPassword) {

        final Usuario usuarioAutenticado = ContextHolder.getAuthenticatedUser();

        Assert.isTrue(usuarioAutenticado.getId().equals(id), MessageSourceHolder.getMessage("security.accessDenied"));
        final Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));

        Assert.notNull(currentPassword, "A senha atual não pode ser vazia.");
        Assert.notNull(newPassword, "A nova senha não pode ser vazia.");

        Assert.isTrue(BCrypt.checkpw(currentPassword, usuario.getSenha()), "A senha atual está incorreta.");

        //somente para fins de validação, sem econdar a senha
        usuario.setSenha(newPassword);
        StandaloneBeanValidation.validate(usuario);

        usuario.setSenha(this.passwordEncoder.encode(newPassword));

        this.usuarioRepository.saveAndFlush(usuario);
    }

    /**
     * Gera e-mail de recuperação de senha
     *
     * @param email String
     */
    public void recoverSenha(final String email) {

        Assert.notNull(email, "O email deve ser preenchido");
        final Usuario usuario = (Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(email);

        Assert.notNull(usuario, "Usuário não encontrado");

        Assert.isTrue(!usuario.isInterno(), "Esse usuário é interno");

        if (!usuario.isEnabled()) {
            throw new DisabledException("Usuário inabilitado");//MessageSourceHolder.getMessage( "authentication.disabledUser", null, LocaleContextHolder.getLocale() ) );
        }

        final String codigo = gerarCodigo();
        final String url = HTTPContextHolder.getServerURL() + "/sistema/#/cadastrar-senha/" + codigo;

        usuario.setCodigo(codigo);
        this.usuarioRepository.saveAndFlush(usuario);

        this.usuarioMailRepository.sendPasswordReset(usuario, url);
    }

    /**
     * Atualiza a senha de um Usuário através da função "Esqueci Minha Senha" com o token
     *
     * @param codigo String
     * @param senha  String
     */
    public void resetSenha(final String codigo, final String senha) {
        Assert.notNull(codigo, "O código de segurança deve ser informado");
        final Usuario usuario = this.usuarioRepository.findByCodigo(codigo);

        Assert.isTrue(!usuario.isInterno(), "Esse usuário é interno");

        //somente para fins de validação, sem encodar a senha
        usuario.setSenha(senha);
        StandaloneBeanValidation.validate(usuario);

        usuario.setSenha(this.passwordEncoder.encode(senha));
        usuario.setCodigo(null);

        this.usuarioRepository.saveAndFlush(usuario);
    }

    /**
     * @param username String
     * @return Usuario
     */
    public Usuario findByUsername(String username) {
        final Usuario ldapUser = this.findByLdapUsername(username);
        if (ldapUser != null) {
            return (Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(ldapUser.getEmail());
        }
        return (Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(username);
    }

    /**
     * @param username {String}
     * @return Usuario
     */
    public Usuario findByLdapUsername(final String username) {

        final LdapQuery query = LdapQueryBuilder.query()
                .base(baseDn)
                .where("SAMAccountName").is(Usuario.extractUsernameFromEmail(username));

        final List<Usuario> usuarios = this.ldapTemplate.search(query, (AttributesMapper<Usuario>) attributes -> {
            final Usuario usuario = new Usuario();

            if (attributes.get("sAMAccountName") != null) {
                usuario.setUsername((String) attributes.get("sAMAccountName").get());
            }

            if (attributes.get("cn") != null) {
                usuario.setNome((String) attributes.get("cn").get());
            }

            if (attributes.get("mail") != null) {
                usuario.setEmail((String) attributes.get("mail").get());
            } else if (attributes.get("userPrincipalName") != null) {
                usuario.setEmail((String) attributes.get("userPrincipalName").get());
            } else {
                usuario.setEmail("naocadastrado@pti.org.br");
            }
            return usuario;
        });

        return (usuarios == null || usuarios.isEmpty()) ? null : usuarios.get(0);
    }

    /**
     * @param id {Long}
     * @return Set<GrantedAuthority>
     */
    public Set<GrantedAuthority> getAuthoritiesByUsuarioId(final long id) {

        final Usuario usuario = this.findById(id);

        if (usuario.isAdministrador()) {
            return Stream.of(Permissao.getAdministratorInstance()).collect(Collectors.toCollection(HashSet::new));
        }

        if (usuario.getAuthorities() == null || usuario.getAuthorities().size() == 0)
            return null;

        final Set<GrantedAuthority> authorities = new HashSet<>();
        usuario.getAuthorities().forEach(authority -> {
            final Permissao permissao = new Permissao();
            permissao.setIdentificador(authority.getAuthority());
            authorities.add(permissao);
        });

        return authorities;
    }

    /**
     * @param usuarioId   long
     * @param password    String
     * @param newPassword String
     * @return Usuario
     */
    @SuppressWarnings("unused")
    public Usuario changePassword(final long usuarioId, final String password, final String newPassword) {
        final Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Objects.requireNonNull(usuario).setSenha(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
        return usuario;
    }
}
