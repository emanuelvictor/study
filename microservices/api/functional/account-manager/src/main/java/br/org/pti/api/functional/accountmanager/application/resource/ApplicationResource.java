package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.Application;
import br.org.pti.api.functional.accountmanager.domain.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


    /**
     *
     */
    //TODO make in bach in future
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{clientId}/notify")
    public Boolean notifyClient(@PathVariable final String clientId) {
        return applicationService.notify(clientId);
    }

}
