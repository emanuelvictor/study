//package br.org.pti.api.nonfunctional.authengine.application.resource;
//
//import br.org.pti.api.nonfunctional.authengine.domain.entities.Permission;
//import br.org.pti.api.nonfunctional.authengine.domain.entities.User;
//import br.org.pti.api.nonfunctional.authengine.domain.services.ServiceToken;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.Set;
//
///**
// * Created by emanuelvictor on 08/04/15.
// */
//@Controller
//@RequiredArgsConstructor
//public class TokenResource {
//
//    /**
//     *
//     */
//    private final ServiceToken serviceToken;
//
//    /**
//     * @param refreshToken String
//     */
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @RequestMapping(value = "/oauth/revoke/{refreshToken}", method = RequestMethod.DELETE)
//    void revokeToken(@PathVariable("refreshToken") final String refreshToken) {
//        serviceToken.removeRefreshToken(refreshToken);
//    }
//
//    /**
//     * @param token String
//     * @return ResponseEntity<User>
//     */
//    @RequestMapping(value = "/oauth/principal/{token}", method = RequestMethod.GET)
//    ResponseEntity<User> getPrincipal(@PathVariable("token") final String token) {
//        return ResponseEntity.ok(serviceToken.getPrincipal(token));
//    }
//
//
//    /**
//     * @param token String
//     * @return ResponseEntity<Set < GrantedAuthority>>
//     */
//    @RequestMapping(value = "/oauth/authorities/{token}", method = RequestMethod.GET)
//    ResponseEntity<Set<Permission>> getAuthoritiesByToken(@PathVariable("token") final String token) {
//        return ResponseEntity.ok(serviceToken.getPrincipal(token).getAuthorities());
//    }
//
//    /**
//     * @return
//     */
//    @RequestMapping("/api/user")
//    public ResponseEntity<User> getPrincipal() {
//        //Build some dummy data to return for testing
//        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        final User profile = new User();
//        profile.setUsername(user.getUsername());
//
//        return ResponseEntity.ok(profile);
//    }
//}
