package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.rh.Funcionario;
import br.org.pti.integrador.domain.entities.rh.SituacaoFolha;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2017
 */
@Repository
public interface FuncionarioRepository extends ProtheusRepository<Funcionario, Long> {

    /**
     * 
     * @param email
     * @param situacaoFolha
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE fu.email = :email "
            + "AND fu.situacaoFolha = :situacaoFolha ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Funcionario> findByEmailAndSituacaoFolha(String email, SituacaoFolha situacaoFolha);
    
    /**
     * 
     * @param matricula
     * @param situacaoFolha
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE fu.matricula = :matricula "
            + "AND fu.situacaoFolha = :situacaoFolha ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Funcionario> findByMatriculaAndSituacaoFolha(String matricula, SituacaoFolha situacaoFolha);
    
    /**
     * 
     * @param nome
     * @param situacaoFolha
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE UPPER(fu.nome) LIKE CONCAT(UPPER(:nome), '%') "
            + "AND fu.situacaoFolha = :situacaoFolha ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Page<Funcionario> findByNomeAndSituacaoFolha(String nome, SituacaoFolha situacaoFolha, Pageable pageable);
    
    /**
     * 
     * @param codigo
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE fu.situacaoFolha <> 'D' "
            + "AND fu.departamento.codigo = :codigo ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Page<Funcionario> findByDepartamentoAndSituacaoFolha(String codigo, Pageable pageable);
    
    /**
     * 
     * @param matricula
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE fu.matricula = :matricula ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Funcionario findByMatricula(String matricula);    
    
    /**
     * 
     * @param centroCusto
     * @param pageable
     * @return 
     */
    @Query("FROM Funcionario fu "
            + "WHERE fu.situacaoFolha <> 'D' "
            + "AND trim(fu.centroCusto.codigo) = :centroCusto ")
    @EntityGraph(value = "Funcionario.completo", type = EntityGraph.EntityGraphType.FETCH)
    Page<Funcionario> findByCentroCusto(String centroCusto, Pageable pageable);
}
