package br.org.pti.api.nonfunctional.authengine.application.security.custom;

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

        if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
            if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                    this.accessTokens.add(new AccessTokenAuthentication(((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId(), token));
        return null;
    }

    public OAuth2AccessToken getOAuth2AccessToken(final OAuth2Authentication authentication) {

        if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
            if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                    for (AccessTokenAuthentication accessToken : this.accessTokens) {
                        if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId().equals(accessToken.getSessionId()))
                            return accessToken.getToken();
                    }

        return null;
    }

    public OAuth2RefreshToken putOAuth2RefreshToken(final OAuth2Authentication authentication, final OAuth2RefreshToken token) {

        if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
            if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                    this.refreshTokens.add(new RefreshTokenAuthentication(((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId(), token));
        return null;
    }

    public OAuth2RefreshToken getOAuth2RefreshToken(final OAuth2Authentication authentication) {

        if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
            if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                    for (RefreshTokenAuthentication refreshToken : this.refreshTokens) {
                        if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId().equals(refreshToken.getSessionId()))
                            return refreshToken.getToken();
                    }
        return null;
    }

}
