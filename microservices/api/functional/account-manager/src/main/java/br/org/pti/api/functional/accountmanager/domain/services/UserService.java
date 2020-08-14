package br.org.pti.api.functional.accountmanager.domain.services;

import br.org.pti.api.functional.accountmanager.domain.entities.User;
import br.org.pti.api.functional.accountmanager.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    /**
     *
     */
    private final UserRepository userRepository;

    /**
     * @param pageable
     * @return
     */
    public Page<User> listByFilters(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public User loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not founded!"));
    }
}
