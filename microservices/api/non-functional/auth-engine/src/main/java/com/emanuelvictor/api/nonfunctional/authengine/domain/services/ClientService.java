package com.emanuelvictor.api.nonfunctional.authengine.domain.services;

import com.emanuelvictor.api.nonfunctional.authengine.domain.entities.ClientBuilder;
import com.emanuelvictor.api.nonfunctional.authengine.domain.entities.GrantType;
import com.emanuelvictor.api.nonfunctional.authengine.domain.repositories.feign.IClientFeignRepository;
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

    private static final String[] ALL_GRANT_TYPES = new String[]{GrantType.AUTHORIZATION_CODE.getValue(), GrantType.CLIENT_CREDENTIALS.getValue(), GrantType.IMPLICIT.getValue(), GrantType.PASSWORD.getValue(), GrantType.REFRESH_TOKEN.getValue(), "implicit"};

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
                    .withRedirectUris("http://localhost:8081/login", "http://localhost:8084/api/logged", "http://localhost:8080/account-manager/api/logged", "http://localhost:8084/api/test") //TODO api/test
                    .withScoped(false)
                    .withClientSecret(passwordEncoder.encode(env.getProperty("oauth.clientSecret")))
                    .withScope("root")
                    .withSecretRequired(true)
                    .withAuthorizedGrantTypes(ALL_GRANT_TYPES)
                    .build();
        }

        return this.clientFeignRepository.loadClientByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

}
