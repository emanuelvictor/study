package com.emanuelvictor.api.nonfunctional.authengine.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
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
    private Long id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     *
     */
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
    private String name;

    /**
     *
     */
    private AccessGroup accessGroup;

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

    /**
     * Percorre recursivamente as permissões e retorna elas lineares.
     *
     * @param permissions Set<Permission>
     * @return Set<Permissao>
     */
    private static Set<Permission> populePermissions(final Set<Permission> permissions) {

        final Set<Permission> localPermissions = new HashSet<>();

        permissions.forEach(permission -> {
            localPermissions.add(permission.copy());
            if (!permission.getLowerPermissions().isEmpty())
                localPermissions.addAll(populePermissions(permission.getLowerPermissions()));
        });

        return localPermissions;

    }

    /**
     * Retorna as authorities do usuário.
     *
     * @return Set<GrantedAuthority>
     */
    @Override
    public Set<Permission> getAuthorities() {

        final Set<Permission> permissions = new HashSet<>();

        if (this.accessGroup != null && this.accessGroup.getAccessGroupPermissions() != null)
            for (AccessGroupPermission accessGroupPermission : this.accessGroup.getAccessGroupPermissions()) {
                permissions.add(accessGroupPermission.getPermission().copy());

                if (!accessGroupPermission.getPermission().getLowerPermissions().isEmpty())
                    permissions.addAll(populePermissions(accessGroupPermission.getPermission().getLowerPermissions()));
            }

        return permissions.isEmpty() ? null : new HashSet<>(permissions);

    }

}
