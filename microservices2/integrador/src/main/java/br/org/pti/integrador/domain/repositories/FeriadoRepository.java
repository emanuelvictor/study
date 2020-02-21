package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.pontoeletronico.Feriado;
import br.org.pti.integrador.domain.entities.pontoeletronico.TipoFeriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 2019.02.07
 */
@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Long> {

    /**
     * 
     * @param tipoFeriado
     * @return 
     */
    @Query("FROM Feriado f "
            + "WHERE f.tipoFeriado = ?1 "
            + "  AND  f.delet = false "
            + "  ORDER BY f.mesDia ASC")
    List<Feriado> listarFeriados(TipoFeriado tipoFeriado);

    /**
     * 
     * @param dataInicio
     * @param dataFim
     * @return 
     */
    @Query("FROM Feriado f "
            + "WHERE (f.tipoFeriado = 'S') "
            + "   OR (f.data BETWEEN ?1 AND ?2) "
            + "  AND  f.delet = false "
            + "  ORDER BY f.mesDia ASC")
    List<Feriado> buscaFeriadosPeriodo(LocalDate dataInicio, LocalDate dataFim);
    
}

