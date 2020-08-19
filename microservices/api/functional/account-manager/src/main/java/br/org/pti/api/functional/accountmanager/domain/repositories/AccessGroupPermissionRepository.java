package br.org.pti.api.functional.accountmanager.domain.repositories;

import br.org.pti.api.functional.accountmanager.domain.entities.AccessGroupPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface AccessGroupPermissionRepository extends JpaRepository<AccessGroupPermission, Long> {

    /**
     * @param accessGroupId long
     * @return List<AccessGroupPermission>
     */
    List<AccessGroupPermission> findByAccessGroupId(final long accessGroupId);

}
