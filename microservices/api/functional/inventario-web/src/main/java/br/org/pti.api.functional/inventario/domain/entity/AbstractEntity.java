package br.org.pti.inventario.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
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
     * @param id Long
     */
    AbstractEntity(final Long id) {
        this.id = id;
    }

    /**
     *
     */
    @PrePersist
    protected void refreshCreatedAndUpdated() {
        this.created = LocalDateTime.now();
    }

    /**
     *
     */
    @PreUpdate
    protected void refreshUpdated() {
        this.updated = LocalDateTime.now();
    }
}
