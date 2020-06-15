package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.domain.entities.ClientBuilder;
import br.org.pti.api.nonfunctional.authengine.domain.entities.GrantType;
import br.org.pti.api.nonfunctional.authengine.domain.repositories.feign.IClientFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Service
@RequiredArgsConstructor
public class ClientService implements ClientDetailsService {

    private static final String[] ALL_GRANT_TYPES = new String[]{GrantType.AUTHORIZATION_CODE.getGrantType(), GrantType.CLIENT_CREDENTIALS.getGrantType(), GrantType.IMPLICIT.getGrantType(), GrantType.PASSWORD.getGrantType(), GrantType.REFRESH_TOKEN.getGrantType(), "implicit"};

    /**
     *
     */
    private final PasswordEncoder passwordEncoder;

    /**
     *
     */
    private final IClientFeignRepository clientFeignRepository;

    /**
     *
     */
    private final org.springframework.core.env.Environment env;

    /**
     * @param clientId String
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {

        if (clientId.equals(env.getProperty("oauth.clientId"))) {
            return new ClientBuilder()
                    .withClientId(env.getProperty("oauth.clientId"))
                    .withRedirectUris("http://localhost:8080")
                    .withScoped(false)
                    .withClientSecret(passwordEncoder.encode(env.getProperty("oauth.clientSecret")))
                    .withScope("root")
                    .withSecretRequired(true)
                    .withAuthorizedGrantTypes(ALL_GRANT_TYPES)
                    .build();
        }

        if (clientId.equals("browser")) {
            return new ClientBuilder()
                    .withClientId("browser")
                    .withRedirectUris("http://localhost:8080/test", "http://localhost:8080/test/login")
                    .withScoped(false)
                    .withClientSecret(passwordEncoder.encode("browser"))
//                    .withScope("none")
                    .withSecretRequired(false)
                    .withAccessTokenValiditySeconds(999999999)
                    .withRefreshTokenValiditySeconds(999999999)
                    .withAuthorizedGrantTypes(ALL_GRANT_TYPES)
                    .build();
        }


        //TODO não está convertendo de lá pra cá
        return this.clientFeignRepository.loadClientByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

}
