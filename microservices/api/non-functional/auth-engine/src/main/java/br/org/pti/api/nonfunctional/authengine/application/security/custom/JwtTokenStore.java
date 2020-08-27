package br.org.pti.api.nonfunctional.authengine.application.security.custom;

/*
 * Copyright 2013-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */


import br.org.pti.api.nonfunctional.authengine.domain.entities.GrantType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.*;

/**
 * A {@link TokenStore} implementation that just reads data from the tokens themselves. Not really a store since it
 * never persists anything, and methods like {@link #getAccessToken(OAuth2Authentication)} always return null. But
 * nevertheless a useful tool since it translates access tokens to and from authentications. Use this wherever a
 * {@link TokenStore} is needed, but remember to use the same {@link JwtAccessTokenConverter} instance (or one with the same
 * verifier) as was used when the tokens were minted.
 *
 * @author Dave Syer
 */
public class JwtTokenStore implements TokenStore {

    private JwtAccessTokenConverter jwtTokenEnhancer;

    private ApprovalStore approvalStore;

    /**
     *
     */
    private final List<AccessTokenAuthentication> accessTokens = new ArrayList<>();

    /**
     *
     */
    private final List<RefreshTokenAuthentication> refreshTokens = new ArrayList<>();

    /**
     * Create a JwtTokenStore with this token enhancer (should be shared with the DefaultTokenServices if used).
     *
     * @param jwtTokenEnhancer
     */
    public JwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer) {
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    /**
     * ApprovalStore to be used to validate and restrict refresh tokens.
     *
     * @param approvalStore the approvalStore to set
     */
    public void setApprovalStore(ApprovalStore approvalStore) {
        this.approvalStore = approvalStore;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        return jwtTokenEnhancer.extractAuthentication(jwtTokenEnhancer.decode(token));
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        this.accessTokens.add(new AccessTokenAuthentication(token, authentication));
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = convertAccessToken(tokenValue);
        if (jwtTokenEnhancer.isRefreshToken(accessToken)) {
            throw new InvalidTokenException("Encoded token is a refresh token");
        }
        return accessToken;
    }

    private OAuth2AccessToken convertAccessToken(String tokenValue) {
        return jwtTokenEnhancer.extractAccessToken(tokenValue, jwtTokenEnhancer.decode(tokenValue));
    }

    @Override
    public void removeAccessToken(final OAuth2AccessToken token) {
        for (int i = 0; i < this.accessTokens.size(); i++)
            if (this.accessTokens.get(i).getToken().getValue().equals(token.getValue())) {
                this.accessTokens.remove(i);
                break;
            }
    }

    @Override
    public void storeRefreshToken(final OAuth2RefreshToken refreshToken, final OAuth2Authentication authentication) {
        this.refreshTokens.add(new RefreshTokenAuthentication(refreshToken, authentication));
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        OAuth2AccessToken encodedRefreshToken = convertAccessToken(tokenValue);
        OAuth2RefreshToken refreshToken = createRefreshToken(encodedRefreshToken);
        if (approvalStore != null) {
            OAuth2Authentication authentication = readAuthentication(tokenValue);
            if (authentication.getUserAuthentication() != null) {
                String userId = authentication.getUserAuthentication().getName();
                String clientId = authentication.getOAuth2Request().getClientId();
                Collection<Approval> approvals = approvalStore.getApprovals(userId, clientId);
                Collection<String> approvedScopes = new HashSet<String>();
                for (Approval approval : approvals) {
                    if (approval.isApproved()) {
                        approvedScopes.add(approval.getScope());
                    }
                }
                if (!approvedScopes.containsAll(authentication.getOAuth2Request().getScope())) {
                    return null;
                }
            }
        }
        return refreshToken;
    }

    private OAuth2RefreshToken createRefreshToken(OAuth2AccessToken encodedRefreshToken) {
        if (!jwtTokenEnhancer.isRefreshToken(encodedRefreshToken)) {
            throw new InvalidTokenException("Encoded token is not a refresh token");
        }
        if (encodedRefreshToken.getExpiration() != null) {
            return new DefaultExpiringOAuth2RefreshToken(encodedRefreshToken.getValue(),
                    encodedRefreshToken.getExpiration());
        }
        return new DefaultOAuth2RefreshToken(encodedRefreshToken.getValue());
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        remove(token.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        //TODO
        // gh-807 Approvals (if any) should only be removed when Refresh Tokens are removed (or expired)
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equals(GrantType.AUTHORIZATION_CODE.getGrantType()))
            if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null)
                if (authentication.getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                    if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
                        for (final AccessTokenAuthentication accessToken : this.accessTokens)
                            if (accessToken.getAuthentication() != null && accessToken.getAuthentication().getUserAuthentication() != null && accessToken.getAuthentication().getUserAuthentication().getDetails() != null && accessToken.getAuthentication().getUserAuthentication().getDetails() instanceof WebAuthenticationDetails)
                                if (((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId().equals(((WebAuthenticationDetails) accessToken.getAuthentication().getUserAuthentication().getDetails()).getSessionId()))
                                    return accessToken.getToken();
                                //TODO colocar outros grantypes
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(final String clientId, final String userName) {
        return Collections.emptySet(); //TODO
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(final String clientId) {
        final Set<OAuth2AccessToken> accessTokensToReturn = new HashSet<>();
        for (final AccessTokenAuthentication accessToken : this.accessTokens)
            if (accessToken.getAuthentication() != null && accessToken.getAuthentication().getOAuth2Request() != null && accessToken.getAuthentication().getOAuth2Request().getClientId() != null && accessToken.getAuthentication().getOAuth2Request().getClientId().equals(clientId))
                accessTokensToReturn.add(accessToken.getToken());
        return accessTokensToReturn;
    }

    public void setTokenEnhancer(JwtAccessTokenConverter tokenEnhancer) {
        this.jwtTokenEnhancer = tokenEnhancer;
    }

    private void remove(String token) {
        if (approvalStore != null) {
            OAuth2Authentication auth = readAuthentication(token);
            String clientId = auth.getOAuth2Request().getClientId();
            Authentication user = auth.getUserAuthentication();
            if (user != null) {
                Collection<Approval> approvals = new ArrayList<Approval>();
                for (String scope : auth.getOAuth2Request().getScope()) {
                    approvals.add(new Approval(user.getName(), clientId, scope, new Date(), ApprovalStatus.APPROVED));
                }
                approvalStore.revokeApprovals(approvals);
            }
        }
    }
}
