package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.application.security.custom.JwtTokenStore;
import br.org.pti.api.nonfunctional.authengine.domain.repositories.feign.IClientFeignRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final IClientFeignRepository clientFeignRepository;

    /**
     * @param sessionId String
     */
    public void removeRefreshTokenBySessionId(final String sessionId) {

        final OAuth2RefreshToken oAuth2RefreshToken = ((JwtTokenStore) tokenStore).getRefreshTokenBySessionId(sessionId);

        this.removeRefreshToken(oAuth2RefreshToken);
    }

    /**
     * @param oAuth2RefreshToken OAuth2RefreshToken
     */
    public void removeRefreshToken(final OAuth2RefreshToken oAuth2RefreshToken) {

        this.notifyClients(oAuth2RefreshToken);

        tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);

        tokenStore.removeRefreshToken(oAuth2RefreshToken);

        LOG.info("Refresh Token " + oAuth2RefreshToken.getValue() + " revoked ");
    }

    /**
     * @param oAuth2RefreshToken OAuth2RefreshToken
     */
    public void notifyClients(final OAuth2RefreshToken oAuth2RefreshToken) {

        final OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(oAuth2RefreshToken.getValue());

        final Set<String> clients = extractClientsId(oAuth2Authentication);

        //TODO make in bach
        clients.forEach(this.clientFeignRepository::notify);
//        clients.forEach(System.out::println);

    }

    /**
     * @param oAuth2Authentication OAuth2Authentication
     * @return Set<String>
     */
    private static Set<String> extractClientsId(final OAuth2Authentication oAuth2Authentication) {
        Objects.requireNonNull(oAuth2Authentication);
        return extractClientsId(oAuth2Authentication.getUserAuthentication());
    }

    /**
     * @param authentication Authentication
     * @return Set<String>
     */
    private static Set<String> extractClientsId(final Authentication authentication) {
        Objects.requireNonNull(authentication);
        return extractClientsId(authentication.getAuthorities());
    }

    /**
     * @param grantedAuthorities Collection<? extends GrantedAuthority>
     * @return Set<String>
     */
    public static Set<String> extractClientsId(final Collection<? extends GrantedAuthority> grantedAuthorities) {
        Objects.requireNonNull(grantedAuthorities);
        return grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.contains("/") ? authority.substring(authority.indexOf("/") + 1) : authority).map(authority -> authority.contains("/") ? authority.substring(0, authority.indexOf("/")) : authority)
                .filter(authority -> !authority.equals("root"))
                .collect(Collectors.toSet());
    }
}
