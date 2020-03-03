package br.org.pti.api.nonfunctional.authorizationserver.domain.entities;

import br.org.pti.api.nonfunctional.authorizationserver.domain.entities.generic.PersistentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"access_group_permission_id", "permission_id"})
})
public class AccessGroupPermission extends PersistentEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Permission permission;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "access_group_permission_id")
    private AccessGroup accessGroup;

    /**
     * @param permission Permissao
     */
    public AccessGroupPermission(final Permission permission) {
        this.permission = permission;
    }
}
