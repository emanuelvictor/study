package br.org.pti.api.nonfunctional.authengine.application.security;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.HashMap;

/**
 *
 */
public final class Codes {

    private static Codes INSTANCE;

    private final HashMap<String, OAuth2Authentication> authorizationCodeStore;

    private Codes() {
        authorizationCodeStore = new HashMap<>();
    }

    public static Codes getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Codes();
        return INSTANCE;
    }

    public HashMap<String, OAuth2Authentication> getAuthorizationCodeStore() {
        return this.authorizationCodeStore;
    }
}
