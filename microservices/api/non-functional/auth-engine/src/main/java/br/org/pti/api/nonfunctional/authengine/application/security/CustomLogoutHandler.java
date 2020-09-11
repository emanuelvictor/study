package br.org.pti.api.nonfunctional.authengine.application.security;

import br.org.pti.api.nonfunctional.authengine.domain.services.ServiceToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    /**
     *
     */
    private final ServiceToken serviceToken;

    /**
     * @param httpServletRequest  HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param authentication      Authentication
     */
    @Override
    public void logout(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Authentication authentication) {
        if (authentication != null && authentication.getDetails() != null && authentication.getDetails() instanceof WebAuthenticationDetails)
            serviceToken.removeTokensBySessionId(((WebAuthenticationDetails) authentication.getDetails()).getSessionId());
    }
}
