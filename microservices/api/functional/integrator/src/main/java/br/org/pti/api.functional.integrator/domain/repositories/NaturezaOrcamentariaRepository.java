package br.org.pti.api.functional.integrator.domain.repositories;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.NaturezaOrcamentaria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 20/11/2019
 */
@Repository
public interface NaturezaOrcamentariaRepository extends JpaRepository<NaturezaOrcamentaria, Long> {

    /**
     * Metodo que retorna uma lista de naturezas orcamentarias
     *
     * @param pageable
     * @return lista de fontes de recurso
     */
    @Query("FROM NaturezaOrcamentaria no WHERE no.delet = false")
    Page<NaturezaOrcamentaria> findAllNotDeleted(Pageable pageable);

    /**
     * Metodo que retorna a natureza orcamentaria caso exista com o codigo infomado
     *
     * @param codigo String
     * @return retorna a natureza orcamentaria
     */
    @Query("FROM NaturezaOrcamentaria no WHERE no.delet = false "
            + "AND TRIM(no.codigo) = ?1 ")
    Optional<NaturezaOrcamentaria> findByCodigo(String codigo);

    /**
     * Metodo que retorna uma lista de naturezas orcamentarias contendo o codigo
     * infomado
     *
     * @param codigo
     * @return
     */
    @Query("FROM NaturezaOrcamentaria no WHERE no.delet = false "
            + "AND TRIM(no.codigo) LIKE '%'||?1||'%' ")
    List<NaturezaOrcamentaria> findByCodigoLike(String codigo);

    /**
     * Metodo que retorna uma lista de naturezas orcamentarias contendo a descricao
     * infomada
     *
     * @param descricao
     * @return
     */
    @Query("FROM NaturezaOrcamentaria no WHERE no.delet = false "
            + "AND TRIM(UPPER(no.descricao)) LIKE UPPER('%'||?1||'%') ")
    List<NaturezaOrcamentaria> findByDescricaoLike(String descricao);
}
