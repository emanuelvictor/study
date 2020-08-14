package br.org.pti.api.nonfunctional.authengine.domain.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
@EqualsAndHashCode
public class Client implements ClientDetails {

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
    private Set<String> authorizedGrantTypes;

    /**
     *
     */
    @Getter
    @Setter
    private Set<String> registeredRedirectUri;

    /**
     *
     */
    @Getter
    @Setter
    private Set<String> resourceIds;

    /**
     *
     */
    @Getter
    @Setter
    private Set<String> scope;

    /**
     *
     */
    @Setter
    private boolean secretRequired;

    /**
     *
     */
    @Setter
    private boolean scoped;

    /**
     *
     */
    @Getter
    @Setter
    private Integer accessTokenValiditySeconds;

    /**
     *
     */
    @Getter
    @Setter
    private Integer refreshTokenValiditySeconds;

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
     * TODO virá do account manager
     *
     * @return Collection<GrantedAuthority>
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.getScope().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        return this.accessGroup.getAccessGroupPermissions().stream().map(AccessGroupPermission::getPermission).distinct().collect(Collectors.toList());
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
