package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.rh.Departamento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    /**
     * 
     * @param codigo
     * @return 
     */
    @Query("FROM Departamento dp WHERE dp.codigo = :codigo")
    @EntityGraph(value = "Departamento.completo", type = EntityGraphType.FETCH)
    Optional<Departamento> findByCodigo(String codigo);
    
    /**
     * 
     * @return 
     */
    @Query("FROM Departamento dp")
    @EntityGraph(value = "Departamento.completo", type = EntityGraphType.FETCH)
    List<Departamento> findAllNotDeleted();
}
