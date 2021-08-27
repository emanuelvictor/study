package br.com.emanuelvictor.books.domain.entity.generic;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RevisionEntity(EntityTrackingRevisionListener.class)
public class Revision implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1770838608009757264L;

	/**
	 * 
	 */
	@RevisionNumber
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * 
	 */
	@RevisionTimestamp
	private long timestamp;

	/**
	 * 
	 */
	private Long userId;
}
