package br.org.pti.api.nonfunctional.authengine.domain.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@FeignClient(name = "users", url = "${oauth.endpoints.users}")
public interface IUserFeignRepository {

    /**
     * @param username
     * @return
     */
    @GetMapping("{username}")
    Optional<UserDetails> loadUserByUsername(@PathVariable("username") final String username);
}
