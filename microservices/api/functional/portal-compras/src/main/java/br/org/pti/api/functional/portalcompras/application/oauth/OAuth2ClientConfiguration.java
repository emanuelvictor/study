package br.org.pti.api.functional.portalcompras.application.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@Configuration
@RequiredArgsConstructor
public class OAuth2ClientConfiguration {

    @Value("${oauth.token}")
    private String tokenUrl;

    @Value("${oauth.clientId:portalcompras}")
    private String clientId;

    @Value("${oauth.clientSecret:portalcompras}")
    private String clientSecret;

    @Value("${oauth.password:portalcompras}")
    private String password;

    /**
     * Configuração do bean de grant_type de clientcredentials do oauth2.
     * Uma vez que se há em mãos o conhecimento do login e a senha
     *
     * @return OAuth2ProtectedResourceDetails
     */
    @Bean
    protected OAuth2ProtectedResourceDetails resource() {
        final ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
//        resource.setUsername(username);
//        resource.setPassword(password);
        return resource;
    }


    @Bean
    public OAuth2RestTemplate restTemplate(final OAuth2ProtectedResourceDetails resource) {
        final AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(atr));
    }

}
