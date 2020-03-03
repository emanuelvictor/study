/**
 *
 */
package br.org.pti.api.nonfunctional.authorizationserver.application.security;

import br.org.pti.api.nonfunctional.authorizationserver.domain.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.List;

/**
 *
 * @author Emanuel Victor
 *
 * @version 1.0.0
 * @since 2.0.0, 20/02/2020
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     *
     */
    private final TokenStore tokenStore;

    /**
     *
     */
    private final List<TokenEnhancer> tokenEnhancers;

    /**
     *
     */
    private final ApplicationService clientDetailsService;

    /**
     *
     */
    private final AuthenticationManager authenticationManager;

    /**
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     */
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {

        // Configuring the tokenEnhancer
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager);

    }

    /**
     *
     * @param clientDetailsServiceConfigurer ClientDetailsServiceConfigurer
     * @throws Exception
     */
    @Override
    public void configure(final ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer.inMemory()
                .withClient("funcionario2")
                .authorizedGrantTypes("password", "refresh_token")
                .redirectUris("http://0.0.0.0:9000/#/")

                .scopes("perfis", "e", "permissões")
                .secret("$2a$10$svrdNy8n1cqasMqcvHPsj.u/b.uPvNW2VdeMhFkzp5E2cSbFZW6Ba")

                .and()

                .withClient("local1")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")

                .scopes("perfis", "e", "permissões")
                .secret("123456");
//        clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
    }

    /**
     *
     * @param authorizationServerSecurityConfigurer AuthorizationServerSecurityConfigurer
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) {
        authorizationServerSecurityConfigurer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

}
