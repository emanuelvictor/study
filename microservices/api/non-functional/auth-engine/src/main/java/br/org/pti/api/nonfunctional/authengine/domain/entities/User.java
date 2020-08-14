package br.org.pti.api.nonfunctional.authengine.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@EqualsAndHashCode
public class User implements UserDetails {

    /**
     *
     */
    private String username;

    /**
     *
     */
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&+,./])[A-Za-z\\d$@!%*#?&+,./]{8,}$", flags = Flag.UNICODE_CASE, message = "A senha deve conter ao menos 8 caracteres com letras, números e um caractere especial.")
    private String password;

    /**
     *
     */
    @NotNull
    private boolean enabled;

    /**
     *
     */
    private Set<Permission> authorities;

    /**
     *
     */
    private boolean accountNonExpired;

    /**
     *
     */
    private boolean accountNonLocked;

    /**
     *
     */
    private boolean credentialsNonExpired;

    /**
     *
     */
    public User() {
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
