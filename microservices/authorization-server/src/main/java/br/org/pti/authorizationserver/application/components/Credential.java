package br.org.pti.authorizationserver.application.components;

import lombok.*;

import java.io.Serializable;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 24/01/2020
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Credential implements Serializable {

    @Getter
    @Setter
    public String username;
    @Getter
    @Setter
    public String password;

    /**
     *
     * @param username
     * @param password
     */
    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
