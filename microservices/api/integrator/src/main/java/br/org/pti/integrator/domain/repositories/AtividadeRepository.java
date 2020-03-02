package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.oracamento.Atividade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 2019.01.14
 */
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    /**
     * Metodo que retorna uma lista de atividades
     *
     * @param pageable
     * @return lista de atividades
     */
    @Query("FROM Atividade at WHERE at.delet = false ")
    Page<Atividade> findAllNotDeleted(Pageable pageable);

    /**
     * Metodo que retorna a atividade caso exista com o codigo informado
     *
     * @param codigo String
     * @return atividade caso exista
     */
    @Query("FROM Atividade at WHERE at.delet = false "
            + "AND TRIM(at.codigo) = ?1 ")
    Optional<Atividade> findByCodigo(String codigo);    
    
    /**
     * 
     * @param descricao
     * @param pageable
     * @return 
     */
    Page<Atividade> findByDescricaoContaining(String descricao, Pageable pageable);
    
}
