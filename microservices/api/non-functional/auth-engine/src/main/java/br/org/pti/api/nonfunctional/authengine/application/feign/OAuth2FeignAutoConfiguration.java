package br.org.pti.api.nonfunctional.authengine.application.feign;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

/**
 * Configura o feign
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Configuration
@RequiredArgsConstructor
public class OAuth2FeignAutoConfiguration {

    /**
     * @param oAuth2RestTemplate OAuth2RestTemplate
     * @return RequestInterceptor
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(final OAuth2RestTemplate oAuth2RestTemplate) {
        return new OAuth2FeignRequestInterceptor(oAuth2RestTemplate);
    }

}
