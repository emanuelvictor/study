package br.com.emanuelvictor.books.domain.entity.generic;

import org.hibernate.envers.RevisionType;

import java.io.Serializable;

public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener
{
	/**
	 * 
	 */
	@Override
	public void newRevision( Object revisionEntity ) {}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void entityChanged( Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity ) {}
}
