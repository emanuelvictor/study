package br.org.pti.api.nonfunctional.authengine.domain.entities;

import br.org.pti.api.nonfunctional.authengine.domain.entities.generic.PersistentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccessGroupPermission extends PersistentEntity {

    /**
     *
     */
    private Permission permission;

    /**
     *
     */
    private AccessGroup accessGroup;

    /**
     * @param permission Permissao
     */
    public AccessGroupPermission(final Permission permission) {
        this.permission = permission;
    }
}
