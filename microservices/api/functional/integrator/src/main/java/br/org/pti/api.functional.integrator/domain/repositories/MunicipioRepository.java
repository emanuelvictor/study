package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.compras.Municipio;
import br.org.pti.integrator.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface MunicipioRepository extends ProtheusRepository<Municipio, Long> {

    /**
     * 
     * @param uf
     * @return 
     */
    List<Municipio> findByEstado(String uf);

    /**
     * 
     * @param pageable
     * @return 
     */
    @Query("FROM Municipio m WHERE m.delet = false ")
    Page<Municipio> findAllNotDeleted(Pageable pageable);

}
