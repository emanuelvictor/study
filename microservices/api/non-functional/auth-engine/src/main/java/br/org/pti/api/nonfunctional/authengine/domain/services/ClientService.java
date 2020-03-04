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

    private static final String[] ALL_GRANT_TYPES = new String[]{GrantType.AUTHORIZATION_CODE.getGrantType(), GrantType.CLIENT_CREDENTIALS.getGrantType(), GrantType.IMPLICIT.getGrantType(), GrantType.PASSWORD.getGrantType(), GrantType.REFRESH_TOKEN.getGrantType()};

    /**
     *
     */
    private final PasswordEncoder passwordEncoder;

    /**
     *
     */
    private final IClientFeignRepository clientFeignRepository;

    /**
     * @param clientId String
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {

        if (clientId.equals("auth-engine")) {
            return new ClientBuilder()
                    .withClientId("auth-engine")
                    .withClientSecret(passwordEncoder.encode("auth-engine"))
                    .withScope("root")
                    .withAuthorizedGrantTypes(ALL_GRANT_TYPES)
                    .withRedirectUris("http://0.0.0.0:9000/#/")
                    .build();
        }

        return this.clientFeignRepository.loadClientByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

}
