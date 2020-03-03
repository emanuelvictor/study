package br.org.pti.api.functional.portalcompras.application.oauth.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * Configura o feign
 */
@Configuration
//@ConditionalOnClass({ Feign.class })
//@ConditionalOnProperty(value = "feign.oauth2.enabled", matchIfMissing = true)
public class OAuth2FeignAutoConfiguration {

    @Bean
//    @ConditionalOnBean(OAuth2RestTemplate.class)
    public RequestInterceptor oauth2FeignRequestInterceptor(final OAuth2RestTemplate oAuth2RestTemplate) {
        return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
    }
}
