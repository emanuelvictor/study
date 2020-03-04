package br.org.pti.api.nonfunctional.authengine.domain.entities;

import br.org.pti.api.nonfunctional.authengine.domain.entities.generic.PersistentEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends PersistentEntity implements ClientDetails {

    /**
     * Corresponds to the clientId
     */
    @Getter
    @Setter
    @NotBlank(message = "A client id must be informed")
    private String clientId;

    /**
     *
     */
    @Getter
    @Setter
    @NotBlank(message = "A client secrete must be informed")
    private String clientSecret;

    /**
     *
     */
    @Getter
    @Setter
    private AccessGroup accessGroup;

    /**
     *
     */
    @Getter
    @Setter
    private Set<String> authorizedGrantTypes;

    /**
     * TODO virá do account manager
     * @return String
     */
    @Override
    public boolean isSecretRequired() {
        return true;
    }

    /**
     * TODO virá do account manager
     * @return boolean
     */
    @Override
    public boolean isScoped() {
        return true;
    }

    /**
     * TODO virá do account manager
     * @return Set<String>
     */
    @Override
    public Set<String> getScope() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(accessGroupPermission -> accessGroupPermission.getPermission().getAuthority()).collect(Collectors.toSet());
    }

    /**
     * TODO virá do account manager
     * @return Collection<GrantedAuthority>
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(AccessGroupPermission::getPermission).distinct().collect(Collectors.toList());
    }

    /**
     * TODO virá do account manager
     * @return Integer
     */
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 60;
    }

    /**
     * TODO virá do account manager
     * @return Integer
     */
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 999999999;
    }

    /**
     * @param scope String
     * @return boolean
     */
    @Override
    public boolean isAutoApprove(final String scope) {
        return true;
    }

    /**
     * TODO virá do account manager
     *
     * @return Set<String></String>
     */
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    /**
     * Non necessary
     *
     * @return Set<String>
     */
    @Override
    public Set<String> getResourceIds() {
        return null;
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
