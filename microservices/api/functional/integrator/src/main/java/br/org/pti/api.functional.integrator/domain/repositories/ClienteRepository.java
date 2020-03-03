package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.compras.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 29/09/2017
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Metodo responsavel por executar uma busca SQL de um cliente pelo codigo e loja
     * 
     * @param codigo
     * @param loja
     * @return Cliente
     */
    @Query("FROM Cliente cl " +
            "WHERE TRIM(cl.codigo) = ?1 " +
            "AND cl.loja = ?2 " +
            "AND cl.delet = false")
    public Cliente findByCodigoAndLoja(String codigo, String loja);
    
    /**
     * 
     * @param codigo
     * @param loja
     * @return 
     */
    @Query("FROM Cliente cl " +
            "WHERE TRIM(cl.codigo) = ?1 " +
            "AND cl.loja = ?2 " +
            "AND cl.delet = false " +
            "AND cl.bloqueado = false")
    public Optional<Cliente> findOptionalByCodigoAndLoja(String codigo, String loja);

    /**
     * Metodo responsavel por executar uma busca SQL de um cliente pelo email
     * 
     * @param email
     * @return Cliente
     */
    @Query("FROM Cliente cl " +
            "WHERE UPPER(TRIM(cl.email)) = UPPER(?1) " +
            "AND cl.delet = false")
    public Cliente findByEmail(String email);

    /**
     * Metodo responsavel por executar uma busca SQL de um cliente pelo documento
     * 
     * @param documento
     * @return Cliente
     */
    @Query("FROM Cliente cl " +
            "WHERE TRIM(cl.documento) = UPPER(?1) " +
            "AND cl.delet = false")    
    public Cliente findByDocumento(String documento);

    /**
     * 
     * @param codigo
     * @param loja
     * @return 
     */
    @Query("SELECT count(*) FROM Cliente cl " +
            "WHERE TRIM(cl.codigo) = ?1 " +
            "AND cl.loja = ?2 " +
            "AND cl.delet = false")
    public long countByCodigoAndLoja(String codigo, String loja);
}
