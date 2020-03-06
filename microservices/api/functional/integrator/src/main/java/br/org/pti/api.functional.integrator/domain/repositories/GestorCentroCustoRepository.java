package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.GestorCentroCusto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author willian.brecher
 * @version 1.0.0
 * @since 1.0.0, 30/08/2019
 */
@Repository
public interface GestorCentroCustoRepository extends JpaRepository<GestorCentroCusto, Long> {

    /**
     * Metodo que retorna uma lista de centros de custo e seus gestores
     *
     * @param pageable Pageable
     * @return lista de centros de custo e seus gestores
     */
    @Query("FROM GestorCentroCusto gc")
    Page<GestorCentroCusto> findAllGestorCentroCustos(final Pageable pageable);

    /**
     * @param pageable Pageable
     * @return Page<GestorCentroCusto>
     */
    @Query("FROM GestorCentroCusto gc"
            + " WHERE trim(gc.centroCusto) = ?1 ")
    Page<GestorCentroCusto> findAllByCentroCusto(final String centroCusto, Pageable pageable);

}
