package br.org.pti.api.functional.accountmanager.domain.repositories;

import br.org.pti.api.functional.accountmanager.domain.entities.AccessGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface AccessGroupRepository extends JpaRepository<AccessGroup, Long> {

    /**
     * @param filter   String
     * @param pageable Pageable
     * @return Page<AccessGroup>
     */
    @Query("FROM AccessGroup accessGroup WHERE" +
            "   (" +
            "       FILTER(:filter, accessGroup.name) = TRUE" +
            "   )"
    )
    Page<AccessGroup> listByFilters(@Param("filter") final String filter, final Pageable pageable);

}
