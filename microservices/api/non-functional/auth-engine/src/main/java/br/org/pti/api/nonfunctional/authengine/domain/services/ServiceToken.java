/**
 *
 */
package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

/**
 * @author emanuelvictor
 */
@Service
@RequiredArgsConstructor
public class ServiceToken {

//    /**
//     *
//     */
//    private final TokenStore tokenStore;

    /**
     *
     */
    private final UserService userService;

    /**
     *
     */
    private final DefaultTokenServices defaultTokenServices;

    /**
     * @param token String
     */
    public void revokeToken(final String token) {
        defaultTokenServices.revokeToken(token);
        System.out.println("Token " + token + " revogado");
    }

    /**
     *
     * @param token String
     * @return User
     */
    public User getPrincipal(final String token) {
        final OAuth2Authentication oAuth2Authentication = defaultTokenServices.loadAuthentication(token);

        return (User) this.userService.loadUserByUsername((String) oAuth2Authentication.getUserAuthentication().getPrincipal());

    }
}
