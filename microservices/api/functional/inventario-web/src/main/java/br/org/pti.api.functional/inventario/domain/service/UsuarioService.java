package br.org.pti.api.functional.inventario.domain.service;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import br.org.pti.api.functional.inventario.application.i18n.MessageSourceHolder;
import br.org.pti.api.functional.inventario.application.ldap.LdapConfig;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Permissao;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.repository.IExecutorRepository;
import br.org.pti.api.functional.inventario.domain.repository.IUsuarioRepository;
import br.org.pti.api.functional.inventario.infrastructure.aid.StandaloneBeanValidation;
import lombok.RequiredArgsConstructor;
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
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

    /**
     *
     */
    private final CentroCustoService centroCustoService;

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
    private final LdapTemplate ldapTemplate;

    /**
     *
     */
    private final LdapConfig ldapConfig;

    /**
     *
     */
    private final IExecutorRepository executorRepository;

    /**
     * @return código (token) aleatório de segurança com 32 caracteres
     */
    private static String gerarCodigo() {
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
        return loadExecutoresAndCentrosCustoByUsuarioId(this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id))));
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    @Transactional
    public Usuario save(final Usuario usuario) {

        usuario.setAtivo(true);

        final String codigo = gerarCodigo();
        usuario.setCodigo(codigo);
        usuario.setSenha(this.passwordEncoder.encode(Usuario.DEFAULT_PASSWORD));

        final Usuario usuarioSaved = this.usuarioRepository.save(usuario);

        if (this.findByLdapUsername(usuario.getUsername()) == null) {
            usuario.setInterno(false);
        } else {
            usuario.setInterno(true);
        }

        return usuarioSaved;
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    @Transactional
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
    @Transactional
    public Usuario updateAtivo(final long id) {

        final Usuario authenticatedUser = ContextHolder.getAuthenticatedUser();

        final Usuario usuarioSaved = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));

        Assert.notNull(usuarioSaved, MessageSourceHolder.getMessage("repository.notFoundById", id));

        if (!authenticatedUser.isRoot())
            Assert.isTrue(!authenticatedUser.getId().equals(usuarioSaved.getId()), MessageSourceHolder.getMessage("security.accessDenied"));

        usuarioSaved.setAtivo(!usuarioSaved.getAtivo());

        return this.usuarioRepository.saveAndFlush(usuarioSaved);
    }

    /**
     * @param id              long
     * @param currentPassword String
     * @param newPassword     String
     */
    @Transactional
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
    @Transactional
    public void recoverSenha(final String email) {

        Assert.notNull(email, "O email deve ser preenchido");
        final Usuario usuario = this.findByEmailIgnoreCaseOrDocumento(email);

        Assert.notNull(usuario, "Usuário não encontrado");

        Assert.isTrue(!usuario.isInterno(), "Esse usuário é interno");

        if (!usuario.isEnabled()) {
            throw new DisabledException("Usuário inabilitado");//MessageSourceHolder.getMessage( "authentication.disabledUser", null, LocaleContextHolder.getLocale() ) );
        }

        final String codigo = gerarCodigo();

        usuario.setCodigo(codigo);
        this.usuarioRepository.saveAndFlush(usuario);
    }

    /**
     * Atualiza a senha de um Usuário através da função "Esqueci Minha Senha" com o token
     *
     * @param codigo String
     * @param senha  String
     */
    @Transactional
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
    @Transactional
    public Usuario findByUsername(String username) {
        final Usuario ldapUser = this.findByLdapUsername(username);
        if (ldapUser != null) {
            return this.findByEmailIgnoreCaseOrDocumento(ldapUser.getEmail());
        }

        return this.findByEmailIgnoreCaseOrDocumento(username);
    }

    /**
     *
     * @param usuario
     * @return
     */
    @Transactional(readOnly = true)
    Usuario loadExecutoresAndCentrosCustoByUsuarioId(final Usuario usuario) {
        if (usuario == null)
            return null;

        if (usuario.getExecutores() == null)
            usuario.setExecutores(new HashSet<>());
        if (usuario.getCentrosCusto() == null)
            usuario.setCentrosCusto(new HashSet<>());

        this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(usuario.getEmail());


        if (usuario.getId() != null) {
            usuario.setExecutores(this.executorRepository.findByUsuarioId(usuario.getId()));
            usuario.setCentrosCusto(this.centroCustoService.findByGestorId(usuario.getId()));
        } else {
            final Usuario usuarioByEmail = ((Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(usuario.getEmail()));
            if (usuarioByEmail != null) {
                usuario.setExecutores(this.executorRepository.findByUsuarioId(usuarioByEmail.getId()));
                usuario.setCentrosCusto(this.centroCustoService.findByGestorId(usuarioByEmail.getId()));
            }
        }

        return usuario;
    }

    /**
     * @param username {String}
     * @return Usuario
     */
    public Usuario findByLdapUsername(final String username) {

        final LdapQuery query = LdapQueryBuilder.query()
                .base(this.ldapConfig.getBaseDN())
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

        return (usuarios == null || usuarios.isEmpty()) ? null : loadExecutoresAndCentrosCustoByUsuarioId(usuarios.get(0));
    }

    /**
     * @param id {Long}
     * @return Set<GrantedAuthority>
     */
    @Transactional
    public Set<GrantedAuthority> getAuthoritiesByUsuarioId(final long id) {

        final Usuario usuario = this.findById(id);

        if (usuario.isRoot()) {
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
     * @param newPassword String
     * @return Usuario
     */
    @Transactional
    public Usuario changePassword(final long usuarioId, final String newPassword) {
        final Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Objects.requireNonNull(usuario).setSenha(passwordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
        return usuario;
    }

    /**
     * @param email String
     * @return Usuario
     */
    @Transactional(readOnly = true)
    public Usuario findByEmailIgnoreCaseOrDocumento(final String email) {
        return loadExecutoresAndCentrosCustoByUsuarioId((Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(email));
    }
}
