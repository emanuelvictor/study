package br.org.pti.api.nonfunctional.authengine.application.security.custom;

import br.org.pti.api.nonfunctional.authengine.domain.entities.GrantType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.*;

/**
 *
 */
public final class Codes {

    private static Codes INSTANCE;

    private final List<AccessTokenAuthentication> accessTokens;

    private final List<RefreshTokenAuthentication> refreshTokens;

    private Codes() {
        accessTokens = new ArrayList<>();
        refreshTokens = new ArrayList<>();
    }

    public static Codes getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Codes();
        return INSTANCE;
    }

    public OAuth2AccessToken putOAuth2AccessToken(final OAuth2Authentication authentication, final OAuth2AccessToken token) {
        this.accessTokens.add(new AccessTokenAuthentication(authentication, token));
        return token;
    }

    public OAuth2AccessToken getOAuth2AccessToken(final OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equals(GrantType.AUTHORIZATION_CODE.getGrantType()))
            if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
                if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                    if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                        for (final AccessTokenAuthentication accessToken : this.accessTokens)
                            if (accessToken.getAuthentication() != null && accessToken.getAuthentication().getUserAuthentication() != null && accessToken.getAuthentication().getUserAuthentication().getDetails() != null && accessToken.getAuthentication().getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId().equals(((WebAuthenticationDetails) accessToken.getAuthentication().getUserAuthentication().getDetails()).getSessionId()))
                                    return accessToken.getToken();
        return null;
    }

    public OAuth2RefreshToken putOAuth2RefreshToken(final OAuth2Authentication authentication, final OAuth2RefreshToken token) {
        this.refreshTokens.add(new RefreshTokenAuthentication(authentication, token));
        return token;
    }

    public OAuth2RefreshToken getOAuth2RefreshToken(final OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equals(GrantType.AUTHORIZATION_CODE.getGrantType()))
            if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
                if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                    if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                        for (final RefreshTokenAuthentication refreshToken : this.refreshTokens)
                            if (refreshToken.getAuthentication() != null && refreshToken.getAuthentication().getUserAuthentication() != null && refreshToken.getAuthentication().getUserAuthentication().getDetails() != null && refreshToken.getAuthentication().getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId().equals(((WebAuthenticationDetails) refreshToken.getAuthentication().getUserAuthentication().getDetails()).getSessionId()))
                                    return refreshToken.getToken();
        return null;
    }

}
