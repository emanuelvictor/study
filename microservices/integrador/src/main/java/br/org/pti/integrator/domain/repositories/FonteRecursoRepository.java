package br.org.pti.integrator.domain.repositories;

import br.org.pti.integrator.domain.entities.contabilidade.FonteDeRecurso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 2019.01.14
 */
@Repository
public interface FonteRecursoRepository extends JpaRepository<FonteDeRecurso, Long> {

    /**
     * Metodo que retorna uma lista de fontes de recurso
     *
     * @param pageable
     * @return lista de fontes de recurso
     */
    @Query("FROM FonteDeRecurso fr WHERE fr.delet = false")
    Page<FonteDeRecurso> findAllNotDeleted(Pageable pageable);

    /**
     * Metodo que retorna a fonte de recurso caso exista com o codigo infomado
     *
     * @param codigo String
     * @return retorna a fonte de recurso
     */
    @Query("FROM FonteDeRecurso fr WHERE fr.delet = false "
            + "AND TRIM(fr.codigo) = ?1 ")
    Optional<FonteDeRecurso> findByCodigo(String codigo);

    /**
     * Metodo que retorna uma lista de fontes de recursos contendo o codigo
     * infomado
     *
     * @param codigo
     * @return
     */
    @Query("FROM FonteDeRecurso fr WHERE fr.delet = false "
            + "AND TRIM(fr.codigo) LIKE '%'||?1||'%' ")
    List<FonteDeRecurso> findByCodigoLike(String codigo);

    /**
     * Metodo que retorna uma lista de fontes de recursos contendo a descricao
     * infomada
     *
     * @param descricao
     * @return
     */
    @Query("FROM FonteDeRecurso fr WHERE fr.delet = false "
            + "AND TRIM(UPPER(fr.descricao)) LIKE UPPER('%'||?1||'%') ")
    List<FonteDeRecurso> findByDescricaoLike(String descricao);
}
