package br.org.pti.authorizationserver.domain.entities;

import br.org.pti.authorizationserver.domain.entities.generic.PersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Entity
@Audited
@ToString
@Table(name = "aplicacao")
@EqualsAndHashCode(callSuper = true)
public class Application extends PersistentEntity /*implements UserDetails*/ implements ClientDetails {

    /**
     * Corresponds to the clientId
     */
    @Getter
    @Setter
    @NotBlank(message = "A client id must be informed")
    @Column(name = "clientId", length = 45, nullable = false)
    private String clientId;

    /**
     *
     */
    @Getter
    @Setter
    @NotBlank(message = "A client secrete must be informed")
    @Column(name = "clientSecret", length = 90, nullable = false)
    private String clientSecret;

    /**
     *
     */
    @Getter
    @Setter
    @Column(name = "ativo", nullable = false)
    private boolean enable;

    /**
     *
     */
    @ManyToOne
    private AccessGroup accessGroup;


    /**
     *
     */
    public Application() {
        this.enable = true;
    }

    /**
     * @return String
     */
    @Override
    public boolean isSecretRequired() {
        return true;
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isScoped() {
        return true;
    }

    /**
     * @return Set<String>
     */
    @Override
    public Set<String> getScope() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(accessGroupPermission -> accessGroupPermission.getPermission().getAuthority()).collect(Collectors.toSet());
    }

    /**
     * @return Set<String>
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        final Set<String> authorizedGrantTypes = new HashSet<>();
        authorizedGrantTypes.add(GrantType.AUTHORIZATION_CODE.getGrantType());
        authorizedGrantTypes.add(GrantType.CLIENT_CREDENTIALS.getGrantType());
        authorizedGrantTypes.add(GrantType.IMPLICIT.getGrantType());
        authorizedGrantTypes.add(GrantType.PASSWORD.getGrantType());
        authorizedGrantTypes.add(GrantType.REFRESH_TOKEN.getGrantType());
        return authorizedGrantTypes;
    }


    /**
     * @return Collection<GrantedAuthority>
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(AccessGroupPermission::getPermission).distinct().collect(Collectors.toList());
    }

    /**
     * @return Integer
     */
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 60;
    }

    /**
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
     * Non necessary for now.
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
