package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.Application;
import br.org.pti.api.functional.accountmanager.domain.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("applications")
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
        return applicationService.loadClientByClientId(clientId);
    }

}
