package br.org.pti.inventario.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractVersionedEntity extends AbstractEntity {

    /**
     *
     */
    @NotNull
    @Version
    @Column(nullable = false)
    private Long version;

    /**
     *
     */
    public AbstractVersionedEntity() {
    }

    /**
     * @param id Long
     */
    public AbstractVersionedEntity(final Long id) {
        super(id);
    }
}
