package br.org.pti.api.nonfunctional.authengine.application.resource;

import br.org.pti.api.nonfunctional.authengine.domain.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class TestResource {
    @RequestMapping("/api/user")
    public ResponseEntity<User> profile() {
        //Build some dummy data to return for testing
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@howtodoinjava.com";

        User profile = new User();
        profile.setUsername(user.getUsername());

        return ResponseEntity.ok(profile);
    }
}
