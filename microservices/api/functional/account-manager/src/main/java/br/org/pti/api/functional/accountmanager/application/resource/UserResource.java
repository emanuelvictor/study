package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.Permission;
import br.org.pti.api.functional.accountmanager.domain.entities.User;
import br.org.pti.api.functional.accountmanager.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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
     * @param defaultFilter String
     * @param enableFilter  Boolean
     * @param pageable      Pageable
     * @return Page<User>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public Page<User> listByFilters(final String defaultFilter, final Boolean enableFilter, final Pageable pageable) {
        return this.userService.listByFilters(defaultFilter, enableFilter, pageable);
    }

    /**
     * @param id long
     * @return User
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public User findById(@PathVariable final long id) {
        return this.userService.findById(id);
    }

    /**
     * @param user User
     * @return User
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public User save(@RequestBody final User user) {
        return this.userService.save(user);
    }

    /**
     * @param id   long
     * @param user User
     * @return User
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public User updateUser(@PathVariable final long id, @RequestBody final User user) {
        return this.userService.save(id, user);
    }

    /**
     * @param id {long}
     * @return boolean
     */
    @PutMapping("/enable")
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    public boolean updateEnable(@RequestBody final long id) {
        return this.userService.updateEnable(id).getEnabled();
    }

    /**
     * @param id Long
     */
    @PutMapping("/update-password/{id}")
    public void updatePassword(@PathVariable final long id, final HttpServletRequest request) {
        final String currentPassword = request.getParameter("actualPassword");
        final String newPassword = request.getParameter("newPassword");

        this.userService.updatePassword(id, currentPassword, newPassword);
    }

    /**
     * @return UserDetails
     */
    @GetMapping("{username}/username")
    public User loadUserByUsername(@PathVariable final String username) {
        return userService.findByUsername(username);
    }

    /**
     * @param id {Long}
     * @return Set<GrantedAuthority>
     */
    @GetMapping("{id}/authorities")
    public Set<Permission> getAuthoritiesByUserId(@PathVariable final long id) {
        return this.userService.getAuthoritiesByUserId(id);
    }

    /**
     * @param userId      long
     * @param newPassword String
     * @return User
     */
    @GetMapping("{userId}/change-password")
    @PreAuthorize("hasAnyAuthority('asdf', 'root')")
    User changePassword(@PathVariable final long userId, @RequestParam final String newPassword) {
        return this.userService.changePassword(userId, newPassword);
    }

}
