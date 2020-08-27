package br.org.pti.api.nonfunctional.authengine.application.security.custom;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Data
public class AccessTokenAuthentication {

    private OAuth2AccessToken token;

    private OAuth2Authentication authentication;

    public AccessTokenAuthentication(final OAuth2Authentication authentication, final OAuth2AccessToken token) {
        this.authentication = authentication;
        this.token = token;
    }

}
