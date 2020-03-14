package br.org.pti.api.functional.accountmanager.domain.repositories;

import br.org.pti.api.functional.accountmanager.domain.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 01/01/2020
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}