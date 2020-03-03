package br.org.pti.api.functional.inventario.domain.entity;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener {

    /**
     * @param revisionEntity Object
     */
    @Override
    public void newRevision(final Object revisionEntity) {
        if (ContextHolder.isAuthenticated()) {
            final Long userId = ContextHolder.getAuthenticatedUser().getId();
            ((Revision) revisionEntity).setUserId(userId);
        }
    }

    /**
     * @param entityClass    Class
     * @param entityName     String
     * @param entityId       Serializable
     * @param revisionType   RevisionType
     * @param revisionEntity Object
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void entityChanged(final Class entityClass, final String entityName, final Serializable entityId, final RevisionType revisionType, final Object revisionEntity) {
        if (ContextHolder.isAuthenticated()) {
            final Long userId = ContextHolder.getAuthenticatedUser().getId();
            ((Revision) revisionEntity).setUserId(userId);
        }
    }
}
