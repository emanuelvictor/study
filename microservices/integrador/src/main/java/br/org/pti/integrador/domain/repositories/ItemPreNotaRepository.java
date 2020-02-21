
package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.contabilidade.ItemPreNota;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 08/11/2017
 */
@Repository
public interface ItemPreNotaRepository extends ProtheusRepository<ItemPreNota, Long> {
    
    
    /**
     * Metodos responsavel por executar uma busca SQL de itens da nota fiscal pelo documento
     * 
     * @param documento numero de documento agrupador dos itens pelo cabecalho 
     * da nota
     * @return lista de itens da nota
     */
    @Query("FROM ItemPreNota i "
            + "WHERE i.notaFiscal.documento = ?1 "
            + "AND i.delet = false")
    List<ItemPreNota> findByDocumento(String documento);
    
    /**
     * Metodo para realizar a remocao do registro pelo id informado
     * 
     * @param id id do registro a ser removido
     */
    @Modifying
    @Query("DELETE FROM ItemPreNota WHERE r_e_c_n_o_ = ?1")
    void physicallyDelete(long id);       
}
