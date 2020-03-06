package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.PreNota;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/10/2017
 */
@Repository
public interface PreNotaRepository extends ProtheusRepository<PreNota, Long> {

    /**
     * Metodos responsavel por executar uma busca SQL de uma nota fiscal pelo numero
     * 
     * @param numero numero da nota para busca
     * @return PreNota
     */
    @Query("FROM PreNota nf "
            + "WHERE nf.numero = ?1 "
            + "AND nf.delet = false")
    PreNota findByNumero(String numero);
    
    /**
     * Metodo responsavel por executar um SQL de verificacao se a nota existe 
     * pelos parametros informados
     * 
     * @param numero numero da nota para busca
     * @param documento numero do documento para busca
     * @return >= 1 : existe / 0 : nao existe
     */
    @Query(value = "SELECT count(*) " +
            "FROM zi2010 nf " +
            "WHERE TRIM(nf.zi2_nfelet) = ?1 " +
            "AND TRIM(nf.zi2_doc) = ?2 " + 
            "AND nf.d_e_l_e_t_ <> '*'", nativeQuery = true)
    long existeNotaFiscal(String numero, String documento);    
    
    /**
     * Metodo responsavel por executar uma busca SQL das notas utilizando o lote
     * como parametro
     * 
     * @param lote lote das notas
     * @return lista de PreNota
     */
    @Query("FROM PreNota nf " +
            "WHERE nf.lote = ?1 " +
            "AND nf.delet = false")
    List<PreNota> findByLote(long lote);    
    
    /**
     * Metodo para remover fisicamente o registro pelo id informado
     * 
     * @param id id do registro a ser deletado
     */
    @Modifying
    @Query("DELETE FROM PreNota WHERE r_e_c_n_o_ = ?1")
    void physicallyDelete(long id);        
}
