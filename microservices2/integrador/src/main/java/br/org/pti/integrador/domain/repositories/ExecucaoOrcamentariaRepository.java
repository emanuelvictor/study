package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrador.domain.entities.contabilidade.FonteDeRecurso;
import br.org.pti.integrador.domain.entities.oracamento.ExecucaoOrcamentaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 2019.01.16
 */
@Repository
public interface ExecucaoOrcamentariaRepository extends JpaRepository<ExecucaoOrcamentaria, Long> {

    /**
     *
     * @param centroCusto
     * @return
     */
    @Query("FROM ExecucaoOrcamentaria eo WHERE eo.delet = false "
            + "AND eo.centroCusto = ?1 "
            + "AND eo.statusExecucaoOrcamentaria = 1 "
            + "AND TRIM(eo.atividade) IS NOT NULL ")
    List<ExecucaoOrcamentaria> findByCentroCusto(CentroCusto centroCusto);

    /**
     *
     * @param fonteDeRecurso
     * @return
     */
    @Query("FROM ExecucaoOrcamentaria eo WHERE eo.delet = false "
            + "AND eo.fonteDeRecurso = ?1 "
            + "AND eo.statusExecucaoOrcamentaria = 1 "
            + "AND TRIM(eo.atividade) IS NOT NULL ")
    List<ExecucaoOrcamentaria> findByFonteDeRecurso(FonteDeRecurso fonteDeRecurso);

    /**
     *
     * @param centroCusto
     * @param fonteDeRecurso
     * @return
     */
    @Query("FROM ExecucaoOrcamentaria eo WHERE eo.delet = false "
            + "AND eo.centroCusto = ?1 "
            + "AND eo.fonteDeRecurso = ?2 "
            + "AND eo.statusExecucaoOrcamentaria = 1 "
            + "AND TRIM(eo.atividade) IS NOT NULL ")
    List<ExecucaoOrcamentaria> findByCentroCustoAndFonteDeRecurso(CentroCusto centroCusto,
                                                                  FonteDeRecurso fonteDeRecurso);

}
