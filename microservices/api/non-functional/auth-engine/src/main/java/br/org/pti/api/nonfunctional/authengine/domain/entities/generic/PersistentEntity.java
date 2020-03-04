package br.org.pti.api.nonfunctional.authengine.domain.entities.generic;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"createdOn", "updatedOn"})
public abstract class PersistentEntity implements IPersistentEntity<Long> {

    /**
     *
     */
    @Getter
    @Setter
    protected Long id;

    /**
     *
     */
    @Getter
    protected LocalDateTime createdOn;

    /**
     *
     */
    @Getter
    protected LocalDateTime updatedOn;

    /**
     * @param id
     */
    public PersistentEntity(final Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc }
     *
     * @return
     */
    @Override
    public boolean isSaved() {
        return this.id != null && this.id != 0;
    }
}
