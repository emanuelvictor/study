package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.Application;
import br.org.pti.api.functional.accountmanager.domain.services.ApplicationService;
import br.org.pti.api.functional.accountmanager.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/applications")
public class ApplicationResource {

    /**
     *
     */
    private final ApplicationService applicationService;

    /**
     * @return ClientDetails
     */
    @GetMapping("{clientId}")
    public Application loadClientByClientId(@PathVariable final String clientId) {
        return (Application) applicationService.loadClientByClientId(clientId);
    }

}
