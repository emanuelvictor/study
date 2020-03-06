package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.compras.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 10/05/2019
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    /**
     * Metodo responsavel por executar uma busca SQL de um fornecedor pelo codigo e loja
     * 
     * @param codigo
     * @param loja
     * @return Fornecedor
     */
    @Query("FROM Fornecedor fn " +
            "WHERE TRIM(fn.codigo) = ?1 " +
            "AND fn.loja = ?2 " +
            "AND fn.delet = false")
    Fornecedor findByCodigoAndLoja(String codigo, String loja);
    
    /**
     * 
     * @param codigo
     * @param loja
     * @return 
     */
    @Query("FROM Fornecedor fn " +
            "WHERE TRIM(fn.codigo) = ?1 " +
            "AND fn.loja = ?2 " +
            "AND fn.delet = false " +
            "AND fn.bloqueado = false")
    Optional<Fornecedor> findOptionalByCodigoAndLoja(String codigo, String loja);
    
    /**
     * Metodo responsavel por executar uma busca SQL de um fornecedor pelo email
     *
     * @param email
     * @return Fornecedor
     */
    @Query("FROM Fornecedor fn "
            + "WHERE UPPER(TRIM(fn.email)) = UPPER(?1) "
            + "AND fn.delet = false")
    Fornecedor findByEmail(String email);
    
   /**
     * Metodo responsavel por executar uma busca SQL de um fornecedor pelo documento
     * 
     * @param documento
     * @return Fornecedor
     */
    @Query("FROM Fornecedor fn " +
            "WHERE TRIM(fn.documento) = UPPER(?1) " +
            "AND fn.delet = false")    
    Fornecedor findByDocumento(String documento);

    /**
     * 
     * @param codigo
     * @param loja
     * @return 
     */
    @Query("SELECT count(*) FROM Fornecedor fn " +
            "WHERE TRIM(fn.codigo) = ?1 " +
            "AND fn.loja = ?2 " +
            "AND fn.delet = false")
    long countByCodigoAndLoja(String codigo, String loja);    
}
