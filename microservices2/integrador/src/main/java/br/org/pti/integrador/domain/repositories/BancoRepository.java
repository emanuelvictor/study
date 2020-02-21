package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.financeiro.Banco;
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
 * @since 1.0.0, 30/04/2019
 */
@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

    /**
     * Metodo de busca paginada de bancos cadastrados no Protheus
     *
     * @return lista de bancos
     */
    @Query("FROM Banco b")
    List<Banco> findAll();

    /**
     * Metodo de busca do banco utilizando o codigo
     *
     * @param codigo
     * @return banco
     */
    @Query("FROM Banco b "
            + " WHERE TRIM(b.codigo) = ?1 ")
    Optional<Banco> findByCodigo(String codigo);

    /**
     *
     * @param codigo
     * @return
     */
    @Query("FROM Banco b "
        + "WHERE UPPER(b.descricao) LIKE UPPER('%'||?1||'%') ")
    List<Banco> findByDescricaoLike(String descricao);
}
