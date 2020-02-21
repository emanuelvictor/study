package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.oracamento.Contrato;
import br.org.pti.integrador.domain.entities.oracamento.ItemContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 2019.01.16
 */
@Repository
public interface ItemContratoRepository extends JpaRepository<ItemContrato, Long> {

    /**
     * @param contrato
     * @return
     */
    @Query("FROM ItemContrato ic WHERE ic.delet = false "
            + "AND ic.contrato =?1 ")
    List<ItemContrato> findByContrato(Contrato contrato);

}
