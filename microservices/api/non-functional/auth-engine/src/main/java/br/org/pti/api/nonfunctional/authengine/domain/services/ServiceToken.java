package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

/**
 * @author emanuelvictor
 */
@Service
@RequiredArgsConstructor
public class ServiceToken {

    private final Logger LOG = LoggerFactory.getLogger(ServiceToken.class);

    /**
     *
     */
    private final TokenStore tokenStore;

    /**
     *
     */
    private final UserService userService;

    /**
     * @param refreshToken String
     */
    public void removeRefreshToken(final String refreshToken) {
        final OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(refreshToken);

        tokenStore.removeRefreshToken(oAuth2RefreshToken);

        LOG.info("Refresh Token " + refreshToken + " revoked ");
    }

    /**
     * @param token String
     * @return User
     */
    public User getPrincipal(final String token) {

        final OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);

        return (User) this.userService.loadUserByUsername((String) oAuth2Authentication.getUserAuthentication().getPrincipal());
    }
}
