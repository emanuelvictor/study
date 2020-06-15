package br.org.pti.api.functional.accountmanager.application.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class TestResource {

    /**
     * @param code
     * @return
     */
    @GetMapping("test")
    public String test(final String code) {
        System.out.println(code);
        return code;
    }

    /**
     * @param code
     * @return
     */
    @GetMapping("logged")
    public String logged(final String code) {
        System.out.println(code);
        return code;
    }

}
