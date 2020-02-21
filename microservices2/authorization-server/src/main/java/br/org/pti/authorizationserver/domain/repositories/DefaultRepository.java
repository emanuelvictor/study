package br.org.pti.authorizationserver.domain.repositories;

import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
public interface DefaultRepository<T extends PersistentEntity> extends JpaRepository<T, Long> { }
