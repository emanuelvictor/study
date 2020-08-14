package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.User;
import br.org.pti.api.functional.accountmanager.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public Page<User> listByFilters(final Pageable pageable) {
        return userService.listByFilters(pageable);
    }

    /**
     * @return UserDetails
     */
    @GetMapping("{username}")
    public User loadUserByUsername(@PathVariable final String username) {
        return userService.loadUserByUsername(username);
    }

}
