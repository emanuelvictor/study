package br.org.pti.api.functional.accountmanager.domain.entities;

import br.org.pti.api.functional.accountmanager.application.converters.StringSetConverter;
import br.org.pti.api.functional.accountmanager.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Entity
@Audited
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"authorities"})
public class Application extends PersistentEntity implements ClientDetails {

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
    @ManyToOne(optional = false)
    private AccessGroup accessGroup;

    /**
     *
     */
    @Getter
    @Setter
    @Column
    @Convert(converter = StringSetConverter.class)
    private Set<String> registeredRedirectUri;

    /**
     *
     */
    @Getter
    @Setter
    @Column
    @Convert(converter = StringSetConverter.class)
    private Set<String> resourceIds;

    @Getter
    @Setter
    @Column
    @Convert(converter = StringSetConverter.class)
    private Set<String> authorizedGrantTypes;

    /**
     *
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private boolean secretRequired;

    /**
     *
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private boolean scoped;

    /**
     *
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private Integer accessTokenValiditySeconds = 60000;

    /**
     *
     */
    @Getter
    @Setter
    @Column(nullable = false)
    private Integer refreshTokenValiditySeconds = 999999999;

    /**
     *
     */
    public Application() {
    }


    /**
     * @return Set<String>
     */
    @Override
    public Set<String> getScope() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(accessGroupPermission -> accessGroupPermission.getPermission().getAuthority()).collect(Collectors.toSet());
    }

    /**
     * @return Collection<GrantedAuthority>
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.accessGroup.getAccessGroupPermissions().stream().map(AccessGroupPermission::getPermission).distinct().collect(Collectors.toList());
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
     * Non necessary
     *
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    //
//    /**
//     * @return Set<String>
//     */
//    @Override
//    public Set<String> getAuthorizedGrantTypes() {
//        final Set<String> authorizedGrantTypes = new HashSet<>();
//        authorizedGrantTypes.add(GrantType.AUTHORIZATION_CODE.getGrantType());
//        authorizedGrantTypes.add(GrantType.CLIENT_CREDENTIALS.getGrantType());
//        authorizedGrantTypes.add(GrantType.IMPLICIT.getGrantType());
//        authorizedGrantTypes.add(GrantType.PASSWORD.getGrantType());
//        authorizedGrantTypes.add(GrantType.REFRESH_TOKEN.getGrantType());
//        return authorizedGrantTypes;
//    }
}
