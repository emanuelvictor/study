package br.org.pti.authorizationserver.domain.entities;

import br.org.pti.authorizationserver.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import java.util.Collections;
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
@JsonIgnoreProperties({"authorities"})
@lombok.EqualsAndHashCode(callSuper = true)
public class User extends PersistentEntity implements UserDetails {

    /**
     *
     */
    public static final String DEFAULT_PASSWORD = "pti#1234";

    /**
     * Máximo de tentativas frustradas de login para bloqueio do usuário
     */
    private static final int MAX_ATTEMPTS = 5;

    /**
     *
     */
    @NotBlank
    @Length(max = 150)
    @Column(nullable = false, length = 150)
    protected String name;

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
    @JsonProperty(access = Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@!%*#?&+,./])[A-Za-z\\d$@!%*#?&+,./]{8,}$", flags = Flag.UNICODE_CASE, message = "A senha deve conter ao menos 8 caracteres com letras, números e um caractere especial.")
    private String password;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean enable;

    /**
     *
     */
    @JsonAlias("isRoot")
    private boolean root;

    /**
     *
     */
    @ManyToOne
    private AccessGroup accessGroup;

    /**
     *
     */
    public User() {
    }

    /**
     * Percorre recursivamente as permissões e retorna elas lineares.
     *
     * @param permissions Set<Permission>
     * @return Set<Permissao>
     */
    private static Set<Permission> populePermissions(final Set<Permission> permissions) {

        final Set<Permission> permissoesLocais = new HashSet<>();

        permissions.forEach(permissao -> {
            permissoesLocais.add(permissao);
            if (!permissao.getLowerPermissions().isEmpty())
                permissoesLocais.addAll(populePermissions(permissao.getLowerPermissions()));
        });

        return permissoesLocais;
    }

    /**
     * Retorna as authorities do usuário.
     *
     * @return Set<GrantedAuthority>
     */
    @Override
    @Transient
    @JsonIgnore
    public Set<GrantedAuthority> getAuthorities() {

        if (root) {
            return new HashSet<>(Collections.singletonList((GrantedAuthority) () -> "root")); // TODO separar em uma variável global
        }

        final Set<Permission> permissoes = new HashSet<>();

        if (this.accessGroup != null && this.accessGroup.getAccessGroupPermissions() != null)
            for (AccessGroupPermission grupoAcessoPermissao : this.accessGroup.getAccessGroupPermissions()) {
                permissoes.add(grupoAcessoPermissao.getPermission());

                if (!grupoAcessoPermissao.getPermission().getLowerPermissions().isEmpty())
                    permissoes.addAll(populePermissions(grupoAcessoPermissao.getPermission().getLowerPermissions()));
            }
        return permissoes.isEmpty() ? null : new HashSet<>(permissoes);
    }

    /**
     * @return String
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.enable;
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return boolean
     */
    @Override
    @JsonIgnore
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
