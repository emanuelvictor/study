package br.org.pti.api.nonfunctional.authengine.domain.services;

import br.org.pti.api.nonfunctional.authengine.domain.repositories.feign.IApplicationFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class ApplicationService implements ClientDetailsService {

    /**
     *
     */
    private final IApplicationFeignRepository applicationFeignRepository;

    /**
     * @param clientId String
     * @return ClientDetails
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
        return this.applicationFeignRepository.loadClientByClientId(clientId)
                .orElseThrow(() -> new UsernameNotFoundException("ClientId " + clientId + " não localizado!"));
    }

}
