package br.org.pti.api.nonfunctional.authengine.domain.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@FeignClient(name = "applications", url = "${oauth.endpoints.applications}")
public interface IClientFeignRepository {

    /**
     * @param clientId
     * @return
     */
    @GetMapping("{clientId}")
    Optional<ClientDetails> loadClientByClientId(@PathVariable("clientId") final String clientId);
}
