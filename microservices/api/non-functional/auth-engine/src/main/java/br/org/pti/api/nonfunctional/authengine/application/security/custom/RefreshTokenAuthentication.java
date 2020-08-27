package br.org.pti.api.nonfunctional.authengine.application.security.custom;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Data
public class RefreshTokenAuthentication {

    private OAuth2RefreshToken token;

    private OAuth2Authentication authentication;

    public RefreshTokenAuthentication(final OAuth2Authentication authentication, final OAuth2RefreshToken token) {
        this.authentication = authentication;
        this.token = token;
    }
}
