package br.org.pti.api.functional.portalcompras.domain.entity.generic;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1016827183036472876L;

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     *
     */
    @Column(nullable = false, updatable = false)
    protected LocalDateTime created;

    /**
     *
     */
    @Column
    protected LocalDateTime updated;

    /**
     *
     */
    AbstractEntity(Long id) {
        this.id = id;
    }

    /**
     *
     */
    @PrePersist
    protected void refreshCreatedAndUpdated() {
        final LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.updated = now;
    }

    /**
     *
     */
    @PreUpdate
    protected void refreshUpdated() {
        this.updated = LocalDateTime.now();
    }
}
