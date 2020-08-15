package br.org.pti.api.nonfunctional.authengine.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Client implements ClientDetails {

    /**
     * Corresponds to the clientId
     */
    private String clientId;

    /**
     *
     */
    private String clientSecret;

    /**
     *
     */
    private Set<String> authorizedGrantTypes;

    /**
     *
     */
    private Set<String> registeredRedirectUri;

    /**
     *
     */
    private Set<String> resourceIds;

    /**
     *
     */
    private Set<String> scope;

    /**
     *
     */
    private boolean secretRequired;

    /**
     *
     */
    private boolean scoped;

    /**
     *
     */
    private Integer accessTokenValiditySeconds;

    /**
     *
     */
    private Integer refreshTokenValiditySeconds;

    /**
     *
     */
    private AccessGroup accessGroup;

    /**
     * @return String
     */
    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isScoped() {
        return scoped;
    }

    /**
     * @return Set<String>
     */
    @Override
    public Set<String> getScope() {
        return this.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
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
    public Set<GrantedAuthority> getAuthorities() {

        final Set<Permission> permissions = new HashSet<>();

        if (this.accessGroup != null && this.accessGroup.getAccessGroupPermissions() != null)
            for (AccessGroupPermission grupoAcessoPermissao : this.accessGroup.getAccessGroupPermissions()) {
                permissions.add(grupoAcessoPermissao.getPermission().copy());

                if (!grupoAcessoPermissao.getPermission().getLowerPermissions().isEmpty())
                    permissions.addAll(populePermissions(grupoAcessoPermissao.getPermission().getLowerPermissions()));
            }

        return permissions.isEmpty() ? null : new HashSet<>(permissions);

    }

    /**
     * TODO
     *
     * @param scope String
     * @return boolean
     */
    @Override
    public boolean isAutoApprove(final String scope) {
        return true;
    }

    /**
     * Non necessary
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
