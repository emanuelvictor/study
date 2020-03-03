package br.org.pti.api.functional.portalcompras.application.context;

import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class ContextHolder {

    /**
     * @return {Usuario}
     */
    public static Usuario getAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Usuario) {
            return (Usuario) authentication.getPrincipal();
        }

        throw new AuthenticationCredentialsNotFoundException("O usuário não está autenticado");
    }

    /**
     * @return boolean
     */
    public static boolean isAuthenticated() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null && authentication.getPrincipal() != null && !authentication.getPrincipal().equals("anonymousUser");
    }

    /**
     * @return boolean
     */
    public static boolean itsMe(final long id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assert.isTrue(isAuthenticated(), "O usuário não está autenticado");

        return ((Usuario) authentication.getPrincipal()).getId().equals(id);
    }

    /**
     * @return boolean
     */
    public static boolean isAdm(final long id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Assert.isTrue(isAuthenticated(), "O usuário não está autenticado");

        return ((Usuario) authentication.getPrincipal()).isAdministrador();
    }

}
