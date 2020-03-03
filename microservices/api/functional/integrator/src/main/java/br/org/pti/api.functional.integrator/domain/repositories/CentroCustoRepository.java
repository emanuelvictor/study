package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.domain.entities.contabilidade.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.5.0, 30/08/2019
 */
@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

    /**
     * Metodo que retorna uma lista de centros de custo
     *
     * @param pageable
     * @return lista de centros de custo
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND cc.dataFim = ' '")
    Page<CentroCusto> findAllNotDeleted(Pageable pageable);

    /**
     *
     * @param classe
     * @param pageable
     * @return
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND cc.classe = ?1 "
            + "AND cc.dataFim = ' '")
    Page<CentroCusto> findAllNotDeletedByClasse(Classe classe, Pageable pageable);

    /**
     * Metodo que retorna um centro de custo
     *
     * @param codigo numero do centro de custo a ser retornado
     * @return centro de custo
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND TRIM(cc.codigo) = ?1 "
            + "AND cc.classe = ?2 "
            + "AND cc.dataFim = ' '")
    Optional<CentroCusto> findByCodigo(String codigo, Classe classe);

    /**
     *
     * @param codigo
     * @param classe
     * @return
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND TRIM(cc.codigo) = ?1 "
            + "AND cc.classe = ?2 "
            + "AND cc.dataFim = ' '")
    Optional<CentroCusto> findByCodigoAndClasse(String codigo, Classe classe);

    /**
     * Metodo que lista centros de custo contendo o codigo informado
     *
     * @param codigo
     * @param pageable
     * @return
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND TRIM(cc.codigo) LIKE '%'||?1||'%' "
            + "AND cc.dataFim = ' '")
    Page<CentroCusto> findByCodigoLike(String codigo, Pageable pageable);

    /**
     * Metodo que lista centros de custo contendo a descricao informada
     *
     * @param descricao
     * @param pageable
     * @return
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND TRIM(UPPER(cc.descricao)) LIKE UPPER('%'||?1||'%') "
            + "AND cc.classe = ?2 "
            + "AND cc.dataFim = ' '")
    Page<CentroCusto> findByDescricaoLike(String descricao, Classe classe,
                                          Pageable pageable);

    /**
     * Metodo para busca de uma lista de centros de custo contendo a descricao e
     * classe informada
     *
     * @param classe
     * @param descricao
     * @param pageable
     * @return
     */
    @Query("FROM CentroCusto cc WHERE cc.delet = false "
            + "AND cc.classe = ?1 "
            + "AND TRIM(UPPER(cc.descricao)) LIKE UPPER('%'||?2||'%') "
            + "AND cc.dataFim = ' '")
    Page<CentroCusto> findByclasseAndDescricaoLike(Classe classe,
                                                   String descricao, Pageable pageable);
}
