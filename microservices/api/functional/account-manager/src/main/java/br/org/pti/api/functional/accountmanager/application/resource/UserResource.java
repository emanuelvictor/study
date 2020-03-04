package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserResource {

    /**
     *
     */
    private final UserService userService;

    /**
     * @return UserDetails
     */
    @GetMapping("{username}")
    public UserDetails loadUserByUsername(@PathVariable final String username) {
        return userService.loadUserByUsername(username);
    }

}
