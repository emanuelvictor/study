package br.org.pti.api.nonfunctional.authengine.application.security.custom;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

@Data
public class AccessTokenAuthentication {

    private String sessionId;

    private OAuth2AccessToken token;

    public AccessTokenAuthentication(final String sessionId, final OAuth2AccessToken token) {
        this.sessionId = sessionId;
        this.token = token;
    }

}
