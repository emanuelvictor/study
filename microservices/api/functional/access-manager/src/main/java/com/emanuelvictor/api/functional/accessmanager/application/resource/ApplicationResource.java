package com.emanuelvictor.api.functional.accessmanager.application.resource;

import com.emanuelvictor.api.functional.accessmanager.domain.entities.Application;
import com.emanuelvictor.api.functional.accessmanager.domain.services.ApplicationService;
import lombok.RequiredArgsConstructor;
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
