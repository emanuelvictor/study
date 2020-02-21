package br.org.pti.compras.application.ldap;

import br.org.pti.compras.domain.entity.configuracao.Pessoa;
import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LdapDbAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;

//    private final LdapTemplate ldapTemplate;

    @Value("${ldap.url:}")
    private String url;
    @Value("${ldap.username:}")
    private String username;
    @Value("${ldap.password:}")
    private String password;
    @Value("${ldap.base:}")
    private String base;
    @Value("${ldap.searchBase:}")
    private String searchBase;

    @Override
    public Authentication authenticate(final Authentication authentication) {

        final String login;
        if (Usuario.loginIsADocumento(authentication.getName()))
            login = Pessoa.validateDocumento(authentication.getName().equals("admin") ? authentication.getName() + "@pti.org.br" : authentication.getName());
        else
            login = authentication.getName().equals("admin") ? authentication.getName() + "@pti.org.br" : authentication.getName();

        final String password = (String) authentication.getCredentials();

        if (login == null || login.isEmpty()) {
            throw new BadCredentialsException("Preencha o campo Usuário");
        }

        if (password == null || password.isEmpty()) {
            throw new BadCredentialsException("Preencha o campo Senha");
        }

        final Usuario usuarioDb = this.usuarioService.findByUsername(login);
        if (usuarioDb == null) {
            throw new BadCredentialsException("Você não tem permissão para acessar esse sistema");
        }

        if (!usuarioDb.isEnabled())
            throw new DisabledException("Não foi possível realizar a autenticação");

        final Usuario usuarioLdap = this.usuarioService.findByLdapUsername(login);
        if (usuarioLdap != null) {
            final LdapContextSource contextSource = new LdapContextSource();
            contextSource.setUrl(this.url);
            contextSource.setBase(this.base);
            contextSource.setUserDn(this.username);
            contextSource.setPassword(this.password);
            contextSource.afterPropertiesSet();

            final LdapTemplate ldapTemplate = new LdapTemplate(contextSource);

            // Perform the authentication.
            final Filter filter = new EqualsFilter("sAMAccountName", Usuario.extractUsernameFromEmail(login));

            final boolean authed = ldapTemplate.authenticate(this.searchBase, filter.encode(), password);

            if (!authed) {
                throw new BadCredentialsException("Você não tem permissão para acessar esse sistema");
            }
        } else {
            if (!BCrypt.checkpw(password, usuarioDb.getSenha())) {
                throw new BadCredentialsException(null);
            }
        }

        final Authentication auth = new UsernamePasswordAuthenticationToken(usuarioDb, password, usuarioDb.getAuthorities());

        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        return auth;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
