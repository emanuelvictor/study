package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.oracamento.Contrato;
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
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    /**
     * 
     * @param codigo
     * @return 
     */
    @Query("FROM Contrato ct WHERE ct.delet = false "
            + "AND TRIM(ct.codigo) = ?1 "
            + "ORDER BY ct.revisao")
    List<Contrato> findByCodigo(String codigo);            
}
