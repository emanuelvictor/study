package br.org.pti.api.functional.accountmanager.domain.entities;

import br.org.pti.api.functional.accountmanager.domain.entities.generic.EntityIdResolver;
import br.org.pti.api.functional.accountmanager.domain.entities.generic.PersistentEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Permission.class,
        resolver = EntityIdResolver.class)
public class Permission extends PersistentEntity {

    /**
     *
     */
    @NotNull
    @Column(nullable = false, unique = true)
    private String authority;

    /**
     *
     */
    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER)
    private Permission upperPermission;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upperPermission")
    private Set<Permission> lowerPermissions;

    /**
     *
     */
    public Permission() {
    }

    /**
     * @param authority        String
     * @param upperPermission  Permission
     * @param lowerPermissions Set<Permission>
     */
    public Permission(final String authority,
                      final Permission upperPermission,
                      final Set<Permission> lowerPermissions) {
        this.authority = authority;
        this.upperPermission = upperPermission;
        this.lowerPermissions = lowerPermissions;
    }

    /**
     * @return Permission
     */
    public Permission copy() {
        return new Permission(authority, upperPermission, lowerPermissions);
    }
}
