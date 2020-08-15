package br.org.pti.api.functional.accountmanager.domain.entities;

import br.org.pti.api.functional.accountmanager.application.converters.StringSetConverter;
import br.org.pti.api.functional.accountmanager.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 *
 */
@Entity
@Audited
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({"authorities"})
public class Application extends PersistentEntity {

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

    /**
     *
     */
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

//    /**
//     * @return Set<String>
//     */
//    @Override
//    public Set<String> getScope() {
//        return this.accessGroup.getAccessGroupPermissions().stream().map(accessGroupPermission -> accessGroupPermission.getPermission().getAuthority()).collect(Collectors.toSet());
//    }
//
//    /**
//     * @return Collection<GrantedAuthority>
//     */
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        return this.accessGroup.getAccessGroupPermissions().stream().map(AccessGroupPermission::getPermission).distinct().collect(Collectors.toList());
//    }

}
