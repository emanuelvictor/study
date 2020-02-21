package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.contabilidade.ContaContabil;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@Repository
public interface ContaContabilRepository extends ProtheusRepository<ContaContabil, Long> {

    /**
     * Metodo para retornar a conta contabil pelo numero infomado
     * 
     * @param numero numero da conta
     * @return conta contabil
     */
    @Query("FROM ContaContabil c "
            + "WHERE trim(c.conta) = ?1 "
            + "AND c.delet = false")
    ContaContabil findByNumero(String numero);

    /**
     * Metodo para retornar lista paginada de contas contabeis
     * 
     * @param pageable
     * @return lista de contas contabeis paginada
     */
    @Query("FROM ContaContabil c "
            + "WHERE c.delet = false "
            + "AND c.classe = '2' "
            + "AND c.bloqueada = '2'")
    Page<ContaContabil> findAllNotDeleted(Pageable pageable);

}
