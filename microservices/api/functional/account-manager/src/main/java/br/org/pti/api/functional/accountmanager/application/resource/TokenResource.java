package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("tokens")
public class TokenResource {

    /**
     *
     */
    private final TokenService tokenService;

    /**
     *
     */
    @DeleteMapping("/revoke/{token}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String token) {
        tokenService.revoke(token);
    }

}
