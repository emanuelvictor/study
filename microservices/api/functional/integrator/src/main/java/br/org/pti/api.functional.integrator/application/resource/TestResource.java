package br.org.pti.api.functional.integrator.application.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 14/01/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/testing")
public class TestResource {

    /**
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('root')")
    public StringBuilder buscarPorCodigo() {
        return new StringBuilder("accessed");
    }

    /**
     * @return
     */
    @GetMapping("not-access")
    @PreAuthorize("#oauth2.hasScope('read')")
    public StringBuilder notAccess() {
        return new StringBuilder("not-accessed");
    }

}
