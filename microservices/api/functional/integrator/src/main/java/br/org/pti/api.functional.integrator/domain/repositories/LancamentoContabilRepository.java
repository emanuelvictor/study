package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.contabilidade.ContaContabil;
import br.org.pti.integrator.domain.entities.contabilidade.LancamentoContabil;
import br.org.pti.integrator.domain.entities.contabilidade.StatusConciliacao;
import br.org.pti.integrator.domain.entities.dto.LancamentoViagemValorDTO;
import br.org.pti.integrator.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 27/12/2017
 */
@Repository
public interface LancamentoContabilRepository extends ProtheusRepository<LancamentoContabil, Long> {

    /**
     * Metodo para realizar a busca SQL dos lancamentos contabeis da conta e
     * periodo informado
     *
     * @param beginDate data de inicio do periodo para busca
     * @param endDate data final do periodo para busca
     * @param account conta dos lancamentos contabeis
     * @param pageable obejto com configuracao para paginacao
     * @return lista paginada com os lancamentos contabeis
     */
    @Query(value = "FROM LancamentoContabil l "
            + "WHERE (l.contaCredito = ?3 OR l.contaDebito = ?3) "
            + "AND l.dataLancamento BETWEEN ?1 AND ?2 "
            + "AND l.delet = false")
    Page<LancamentoContabil> findByDate(LocalDate beginDate, LocalDate endDate,
                                        ContaContabil account, Pageable pageable);

    /**
     * Metodo para realizar a busca SQL dos lancamentos contabeis da conta,
     * periodo e status informado
     *
     * @param beginDate data de inicio do periodo para busca
     * @param endDate data final do periodo para busca
     * @param account conta dos lancamentos contabeis
     * @param pageable obejto com configuracao para paginacao
     * @param status status do lancamento a ser buscado
     * @return lista paginada com os lancamentos contabeis
     */
    @Query("FROM LancamentoContabil l "
            + "WHERE ((l.contaCredito = ?3 AND l.conciliadoCredito = ?4) "
            + "OR (l.contaDebito = ?3 AND l.conciliadoDebito = ?4)) "
            + "AND l.dataLancamento BETWEEN ?1 AND ?2 "
            + "AND l.delet = false")
    Page<LancamentoContabil> findByStatus(LocalDate beginDate, LocalDate endDate,
                                          ContaContabil account, StatusConciliacao status, Pageable pageable);

    /**
     * Metodo para realizar a busca SQL dos lancamentos contabeis da conta,
     * periodo e nao conciliado
     *
     * @param beginDate data de inicio do periodo para busca
     * @param endDate data final do periodo para busca
     * @param account conta dos lancamentos contabeis
     * @param pageable obejto com configuracao para paginacao
     * @return lista paginada com os lancamentos contabeis
     */
    @Query("FROM LancamentoContabil l "
            + "WHERE ((l.contaCredito = ?3 AND l.conciliadoCredito <> ?4) "
            + "OR (l.contaDebito = ?3 AND l.conciliadoDebito <> ?4)) "
            + "AND l.dataLancamento BETWEEN ?1 AND ?2 "
            + "AND l.delet = false")
    Page<LancamentoContabil> findByNotConciliatedPage(LocalDate beginDate, LocalDate endDate,
                                                      ContaContabil account, StatusConciliacao status, Pageable pageable);

    /**
     * Metodo para realizar a busca SQL dos lancamentos contabeis da conta,
     * periodo e nao conciliado
     *
     * @param beginDate data de inicio do periodo para busca
     * @param endDate data final do periodo para busca
     * @param account conta dos lancamentos contabeis
     * @return lista paginada com os lancamentos contabeis
     */
    @Query("FROM LancamentoContabil l "
            + "WHERE ((l.contaCredito = ?3 AND l.conciliadoCredito <> ?4) "
            + "OR (l.contaDebito = ?3 AND l.conciliadoDebito <> ?4)) "
            + "AND l.dataLancamento BETWEEN ?1 AND ?2 "
            + "AND l.delet = false")
    List<LancamentoContabil> findByNotConciliatedList(LocalDate beginDate, LocalDate endDate,
                                                      ContaContabil account, StatusConciliacao status);

    /**
     * Metodo para retornar a busca de numeros dos historicos dos acertos e
     * adiantamentos de viagens na tabela de lancamentos contabeis
     *
     * @param account conta dos lancamentos contabeis
     * @param beginDate data de inicio do periodo para busca
     * @param endDate data final do periodo para busca
     * @return lista de numeros de acertos e adiantamentos de viagens
     */
    @Query(value = "WITH lancamentos AS ("
            + "   SELECT REGEXP_REPLACE(ct2_hist, '[^0-9]', '') historico "
            + "     FROM ct2010 "
            + "    WHERE (ct2_credit = ?1)"
            + "      AND ct2_data BETWEEN ?2 AND ?3 "
            + "      AND d_e_l_e_t_ <> '*' "
            + "UNION ALL "
            + "   SELECT REGEXP_REPLACE(ct2_hist, '[^0-9]', '') historico "
            + "     FROM ct2010 "
            + "    WHERE (ct2_debito = ?1) "
            + "      AND ct2_data BETWEEN ?2 AND ?3 "
            + "      AND d_e_l_e_t_ <> '*') "
            + " SELECT DISTINCT historico FROM lancamentos ORDER BY historico",
            nativeQuery = true)
    List<String> findHistoricTravel(String account, String beginDate, String endDate);

    /**
     * Metodo que retorna uma lista de lancamentos com dados de adiantamentos e
     * acertos de viagens
     *
     * @param account conta dos lancamentos contabeis
     * @param number
     * @param endDate data final do periodo para busca
     * @return lista de lancamentos de viagens
     */
    @Query(value = "SELECT new br.org.pti.integrator.domain.entities.dto.LancamentoViagemValorDTO("
            + "   l.valor, "
            + "   CASE "
            + "     WHEN l.contaDebito = ?1 THEN 'D' "
            + "     WHEN l.contaCredito = ?1 THEN 'C' "
            + "    END) "
            + "   FROM LancamentoContabil l "
            + "  WHERE (l.contaDebito = ?1 OR l.contaCredito = ?1) "
            + "    AND l.dataLancamento <= ?3 "
            + "    AND REGEXP_REPLACE(l.historico, '[^0-9]', '') = ?2 "
            + "    AND l.delet = false")
    List<LancamentoViagemValorDTO> findTravelValues(ContaContabil account, String number, LocalDate endDate);

}
