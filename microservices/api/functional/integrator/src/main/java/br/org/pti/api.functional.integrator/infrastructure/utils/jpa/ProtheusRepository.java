package br.org.pti.api.functional.integrator.infrastructure.utils.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T>
 * @param <ID>
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 20/09/17
 */
public interface ProtheusRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     *
     * @param id
     */
    @Modifying
    @Query("update #{#entityName} p set p.delet = false where recno = ?1")
    void markAsDeleted(ID id);

    /**
     *
     * @return
     */
    @Query("FROM #{#entityName} p WHERE p.delet = false")
    List<T> findAllNotDeleted();
}
