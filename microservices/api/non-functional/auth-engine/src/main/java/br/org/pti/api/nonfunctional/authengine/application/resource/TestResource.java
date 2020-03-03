package br.org.pti.api.nonfunctional.authengine.application.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestResource {

    /**
     * @return
     */
    @GetMapping
    public StringBuilder buscarPorCodigo() {
        return new StringBuilder("test");
    }

}
