//package br.org.pti.api.nonfunctional.authengine.application.security;
//
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//
//import java.util.HashMap;
//import java.util.Set;
//
///**
// * Implementation of authorization code services that stores the codes and authentication in memory.
// *
// * @author Ryan Heaton
// * @author Dave Syer
// */
//public class AuthorizationCodeServicesImpl implements AuthorizationCodeServices {
//
//    private final RandomValueStringGenerator generator = new RandomValueStringGenerator();
//
//    @Override
//    public String createAuthorizationCode(final OAuth2Authentication authentication) {
//
//        String code = generator.generate();
//
//        if (authentication.getUserAuthentication() != null && authentication.getUserAuthentication().getDetails() != null && ((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId() != null)
//            code = ((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId();
////        for (String codee : this.getAuthorizationCodeStore().keySet()) {
////            final OAuth2Authentication oAuth2Authentication = this.getAuthorizationCodeStore().get(codee);
////            if (((WebAuthenticationDetails) oAuth2Authentication.getUserAuthentication().getDetails()).getSessionId().equals(((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId()))
////                code = ((WebAuthenticationDetails) authentication.getUserAuthentication().getDetails()).getSessionId();
////        }
//
//        store(code, authentication);
//        return code;
//    }
//
//    @Override
//    public OAuth2Authentication consumeAuthorizationCode(String code)
//            throws InvalidGrantException {
//        OAuth2Authentication auth = this.getAuthorizationCodeStore().get(code);
//        if (auth == null) {
//            throw new InvalidGrantException("Invalid authorization code: " + code);
//        }
//        return auth;
//    }
//
//    public HashMap<String, OAuth2Authentication> getAuthorizationCodeStore() {
//        return Codes.getInstance().getAuthorizationCodeStore();
//    }
//
//    // ------------------------------------------------------------------------------
//
//    public void store(String code, OAuth2Authentication authentication) {
//        this.getAuthorizationCodeStore().put(code, authentication);
//    }
//
//    public OAuth2Authentication remove(String code) {
//        OAuth2Authentication auth = this.getAuthorizationCodeStore().remove(code);
//        return auth;
//    }
//
//}
