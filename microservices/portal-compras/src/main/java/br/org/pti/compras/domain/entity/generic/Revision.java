package br.org.pti.compras.domain.entity.generic;

import br.org.pti.compras.Application;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(schema = Application.REVISION)
@RevisionEntity(EntityTrackingRevisionListener.class)
public class Revision implements Serializable {
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
