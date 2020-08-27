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
public final class AccessAndRefreshTokenStore {

    /**
     *
     */
    private static AccessAndRefreshTokenStore INSTANCE;

    /**
     *
     */
    private final List<AccessTokenAuthentication> accessTokens;

    /**
     *
     */
    private final List<RefreshTokenAuthentication> refreshTokens;

    /**
     *
     */
    private AccessAndRefreshTokenStore() {
        accessTokens = new ArrayList<>();
        refreshTokens = new ArrayList<>();
    }

    /**
     * @return Codes
     */
    public static AccessAndRefreshTokenStore getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AccessAndRefreshTokenStore();
        return INSTANCE;
    }

    /**
     * @param token          OAuth2AccessToken
     * @param authentication OAuth2Authentication
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken storeAccessToken(final OAuth2AccessToken token, final OAuth2Authentication authentication) {
        this.accessTokens.add(new AccessTokenAuthentication(token, authentication));
        return token;
    }

    /**
     * @param authentication OAuth2Authentication
     * @return OAuth2AccessToken
     */
    public OAuth2AccessToken getAccessToken(final OAuth2Authentication authentication) {
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

    /**
     * @param token          OAuth2RefreshToken
     * @param authentication OAuth2Authentication
     * @return OAuth2RefreshToken
     */
    public OAuth2RefreshToken storeRefreshToken(final OAuth2RefreshToken token, final OAuth2Authentication authentication) {
        this.refreshTokens.add(new RefreshTokenAuthentication(token, authentication));
        return token;
    }

    /**
     * @param authentication OAuth2Authentication
     * @return OAuth2RefreshToken
     */
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

    /**
     * @param token OAuth2AccessToken
     */
    public void removeAccessToken(final OAuth2AccessToken token) {
        for (int i = 0; i < this.accessTokens.size(); i++) {
            if (this.accessTokens.get(i).getToken().getValue().equals(token.getValue())) {
                this.accessTokens.remove(i);
                break;
            }
        }
    }

    /**
     * @param clientId String
     * @return Collection<OAuth2AccessToken>
     */
    public Collection<OAuth2AccessToken> findTokensByClientId(final String clientId) {
        final Set<OAuth2AccessToken> accessTokensToReturn = new HashSet<>();
        for (final AccessTokenAuthentication accessToken : this.accessTokens)
            if (accessToken.getAuthentication() != null && accessToken.getAuthentication().getOAuth2Request() != null && accessToken.getAuthentication().getOAuth2Request().getClientId() != null && accessToken.getAuthentication().getOAuth2Request().getClientId().equals(clientId))
                accessTokensToReturn.add(accessToken.getToken());
        return accessTokensToReturn;
    }
}
