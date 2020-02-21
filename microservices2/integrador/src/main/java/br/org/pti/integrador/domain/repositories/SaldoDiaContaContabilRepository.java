package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.contabilidade.ContaContabil;
import br.org.pti.integrador.domain.entities.contabilidade.SaldoDiaContaContabil;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusRepository;
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
 * @since 1.0.0, 02/01/2018
 */
@Repository
public interface SaldoDiaContaContabilRepository extends ProtheusRepository<SaldoDiaContaContabil, Long> {

    /**
     * Metodo para realizar a busca SQL do saldo da conta e data informada
     *
     * @param account numero da conta
     * @param date data do saldo
     * @return SaldoDiaContaContabil
     */
    @Query("FROM SaldoDiaContaContabil sc "
            + "WHERE sc.contaContabil = ?1 "
            + "AND sc.dataSaldo = ?2 "
            + "AND sc.lucroPerda = 'N' "
            + "AND sc.delet = false")
    SaldoDiaContaContabil findByDate(ContaContabil account, LocalDate date);

    /**
     * Metodo para realizar a busca SQL do saldo da conta dentro do periodo
     * informado
     *
     * @param account numero da conta
     * @param beginDate data de inicio da busca pelo saldo da conta
     * @param endDate data final da busca pelo saldo da conta
     * @return saldo do periodo e conta informados
     */
    @Query("FROM SaldoDiaContaContabil sc "
            + "WHERE sc.contaContabil = ?1 "
            + "AND sc.dataSaldo BETWEEN ?2 AND ?3 "
            + "AND sc.lucroPerda = 'N' "
            + "AND sc.delet = false")
    List<SaldoDiaContaContabil> findByPeriod(ContaContabil account, LocalDate beginDate,
                                             LocalDate endDate);

    /**
     * Metodo para realizar a busca SQL do saldo da conta e data informada
     *
     * @param account numero da conta
     * @param date data do saldo
     * @param pageable
     * @return SaldoDiaContaContabil
     */
    @Query("FROM SaldoDiaContaContabil sc "
            + "WHERE sc.contaContabil = ?1 "
            + "AND sc.dataSaldo < ?2 "
            + "AND sc.lucroPerda = 'N' "
            + "AND sc.delet = false ")
    List<SaldoDiaContaContabil> findByLastDate(ContaContabil account,
                                               LocalDate date, Pageable pageable);

    /**
     * Metodo utilizado para retornar os saldos das contas do intervalo
     *
     * @param beginAccount
     * @param endAccount
     * @param beginDate
     * @param endDate
     * @return lista de saldos por intervalo de contas
     */
    @Query(value = "  WITH totais AS ("
            + "     SELECT ct1.ct1_conta conta, ct1.ct1_desc01 descricao,  "
            + "            SUM(cq1.cq1_debito) debito_periodo, SUM(cq1.cq1_credit) credito_periodo  "
            + "       FROM cq1010 cq1 "
            + " INNER JOIN ct1010 ct1 "
            + "         ON ct1.ct1_conta = cq1.cq1_conta "
            + "      WHERE cq1.cq1_conta BETWEEN ?1 AND ?2 "
            + "        AND cq1.cq1_data BETWEEN ?3 AND ?4 "
            + "        AND ct1.ct1_bloq = '2' "
            + "        AND ct1.ct1_classe = '2' "
            + "        AND ct1.d_e_l_e_t_ <> '*' "
            + "        AND cq1.d_e_l_e_t_ <> '*' "
            + "   GROUP BY ct1.ct1_conta, ct1.ct1_desc01 "
            + "   ORDER BY ct1.ct1_conta), "
            + "  "
            + "anterior AS (SELECT * FROM ("
            + "     SELECT cq.cq1_data, cq.cq1_conta, cq.cq1_debito debito_a, cq.cq1_credit credito_a, "
            + "            ROW_NUMBER() OVER (PARTITION BY  cq.cq1_conta ORDER BY cq1_conta, cq.cq1_data ASC) rn"
            + "       FROM cq1010 cq "
            + "  INNER JOIN totais tt "
            + "         ON tt.conta = cq.cq1_conta "
            + "      WHERE cq.d_e_l_e_t_ <> '*' "
            + "        AND cq.cq1_lp = 'N' "
            + "        AND cq.cq1_data <= ?3) "
            + "      WHERE rn = 1) "
            + "  "
            + "     SELECT tt.conta, tt.descricao, tt.credito_periodo creditoPeriodo, tt.debito_periodo debitoPeriodo, "
            + "            COALESCE(ant.credito_a,0) creditoAnterior, COALESCE(ant.debito_a,0) debitoAnterior "
            + "       FROM totais tt "
            + "  INNER JOIN anterior ant "
            + "         ON ant.cq1_conta = tt.conta",
            nativeQuery = true)
    List<Object[]> findByInterval(String beginAccount,
                                  String endAccount, String beginDate, String endDate);
}
