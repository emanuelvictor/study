
package br.org.pti.integrador.domain.repositories;

import br.org.pti.integrador.domain.entities.compras.Produto;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 01/11/2017
 */
@Repository
public interface ProdutoRepository extends ProtheusRepository<Produto, Long> {

    /**
     * Metodo responsavel por executar uma busca SQL de um produto pelo codigo
     *
     * @param codigo
     * @return Produto
     */
    @Query("FROM Produto p "
            + "WHERE TRIM(p.codigo) = ?1 "
            + "AND p.delet = false")
    public Optional<Produto> findOptionalByCodigo(String codigo);
}
