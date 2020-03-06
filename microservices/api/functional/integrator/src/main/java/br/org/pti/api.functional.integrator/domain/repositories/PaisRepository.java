package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.compras.Pais;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 14/05/2019
 */
@Repository
public interface PaisRepository extends ProtheusRepository<Pais, Long> {
    
    /**
     * 
     * @return 
     */
    @Query("FROM Pais p WHERE p.delet = false ORDER BY p.nome ")
    List<Pais> findAllNotDeletedOrdered();
}
