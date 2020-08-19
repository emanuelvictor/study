package br.org.pti.api.functional.accountmanager.domain.entities;

import br.org.pti.api.functional.accountmanager.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import java.util.HashSet;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@Table(name = "\"user\"")
@JsonIgnoreProperties({"authorities"})
@lombok.EqualsAndHashCode(callSuper = true)
public class User extends PersistentEntity {

    /**
     *
     */
    public static final String DEFAULT_PASSWORD = "testeteste";

    /**
     *
     */
    @Column(nullable = false, length = 150, unique = true)
    private String username;

    /**
     *
     */
    @NotBlank
    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&+,./])[A-Za-z\\d$@!%*#?&+,./]{8,}$", flags = Flag.UNICODE_CASE, message = "A senha deve conter ao menos 8 caracteres com letras, números e um caractere especial.")
    private String password;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean enabled;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private boolean locked;

    /**
     *
     */
    @Column(nullable = false, length = 250)
    private String name;

    /**
     *
     */
    @ManyToOne(optional = false)
    private AccessGroup accessGroup;

    /**
     *
     */
    public User() {
    }

    /**
     * @return boolean
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * @return boolean
     */
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * @return boolean
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
