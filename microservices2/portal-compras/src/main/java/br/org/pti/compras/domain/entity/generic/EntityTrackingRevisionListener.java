package br.org.pti.compras.domain.entity.generic;

import br.org.pti.compras.application.context.ContextHolder;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;

public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener {
    /**
     *
     */
    @Override
    public void newRevision(final Object revisionEntity) {
        if (ContextHolder.isAuthenticated()) {
            final Long userId = ContextHolder.getAuthenticatedUser().getId();
            ((Revision) revisionEntity).setUserId(userId);
        }
    }

    /**
     *
     */
    @Override
    @SuppressWarnings("rawtypes")
    public void entityChanged(Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity) {
        if (ContextHolder.isAuthenticated()) {
            final Long userId = ContextHolder.getAuthenticatedUser().getId();
            ((Revision) revisionEntity).setUserId(userId);
        }
    }
}
