package com.emanuelvictor.api.functional.accessmanager.domain.repositories;

import com.emanuelvictor.api.functional.accessmanager.domain.entities.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    /**
     * @param clientId String
     * @return Optional<Application>
     */
    Optional<Application> findByClientId(final String clientId);

    /**
     * @param clientId String
     * @param id       Long
     * @return Optional<Application>
     */
    Optional<Application> findByClientIdAndIdNot(final String clientId, final Long id);

    /**
     * @param filter String
     * @return Page<Application>
     */
    @Query("FROM Application a WHERE filter(:filter, a.clientId) = true")
    Page<Application> findByFiltro(final String filter, final Pageable pageable);

}
