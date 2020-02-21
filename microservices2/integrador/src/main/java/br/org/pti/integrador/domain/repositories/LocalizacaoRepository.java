package br.org.pti.integrador.domain.repositories;


import br.org.pti.integrador.domain.entities.ativofixo.Localizacao;
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
 * @since 1.5.0, 20/09/2019
 */
@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

    /**
     *
     * @param pageable
     * @return
     */
    @Query("FROM Localizacao lo WHERE lo.delet = false")
    Page<Localizacao> findAllNotDeleted(Pageable pageable);

    /**
     *
     * @param codigo
     * @return
     */
    @Query("FROM Localizacao lo WHERE lo.delet = false AND lo.codigo = ?1")
    Optional<Localizacao> findByCodigo(String codigo);

    /**
     *
     * @param descricao
     * @param pageable
     * @return
     */
    @Query("FROM Localizacao lo WHERE lo.delet = false "
            + "AND upper(lo.descricao) LIKE upper('%'||?1||'%')")
    Page<Localizacao> findByDescricao(String descricao, Pageable pageable);
}
