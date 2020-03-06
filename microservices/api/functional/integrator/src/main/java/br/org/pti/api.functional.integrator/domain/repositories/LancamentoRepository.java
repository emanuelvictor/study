package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.PreLancamento;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 15/09/2017
 */
@Repository
public interface LancamentoRepository extends ProtheusRepository<PreLancamento, Long> {

    /**
     *
     * @param lote
     * @return
     */
    @Query("FROM PreLancamento lc " +
            "WHERE lc.lote = ?1 " +
            "AND lc.delet = false")
    List<PreLancamento> findByLote(long lote);

    /**
     *
     * @param codigo
     * @param sequencia
     * @return
     */
    @Query(value = "SELECT count(*) " +
            "FROM ct5010 ct5 " +
            "WHERE ct5.ct5_lanpad = ?1 " +
            "AND ct5.ct5_sequen = ?2 " +
            "AND d_e_l_e_t_ <> '*'", nativeQuery = true)
    long existeLancamentoPadrao(String codigo, String sequencia);
}
