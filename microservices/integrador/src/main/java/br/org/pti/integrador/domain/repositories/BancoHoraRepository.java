package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.pontoeletronico.BancoHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2017
 */
@Repository
public interface BancoHoraRepository extends JpaRepository<BancoHora, Long> {

    /**
     *
     * @param matricula
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    @Query("FROM BancoHora bh JOIN bh.evento ev "
                + "JOIN bh.funcionario fu "
                + "JOIN bh.centroCusto cc "
            + "WHERE fu.matricula = ?1 "
            + "AND bh.dataLancamento BETWEEN ?2 AND ?3 "
            + "ORDER BY bh.dataLancamento ASC")
    List<BancoHora> listarPorMatriculaNoPeriodo(String matricula, LocalDate dataInicial, LocalDate dataFinal);
}
