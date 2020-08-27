package br.org.pti.api.nonfunctional.authengine.application.security.custom;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

@Data
public class RefreshTokenAuthentication {

    private String sessionId;

    private OAuth2RefreshToken token;

    public RefreshTokenAuthentication(final String sessionId, final OAuth2RefreshToken token) {
        this.sessionId = sessionId;
        this.token = token;
    }
}
