package br.org.pti.api.functional.portalcompras.domain.repository;

import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("FROM Categoria categoria WHERE" +
            "   (   " +
            "       ((:ativo IS NOT NULL AND categoria.ativo = :ativo) OR :ativo IS NULL) " +
            "       AND " +
            "       (" +
            "               FILTER(:filter, categoria.nome) = TRUE" +
            "       )" +
            "   )"
    )
    Page<Categoria> listByFilters(@Param("filter") final String filter, @Param("ativo") final Boolean ativo, final Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Categoria" +
            "    SET ativo = CASE WHEN ativo = TRUE THEN FALSE ELSE TRUE END" +
            "    WHERE id = :id")
    void updateAtivo(@Param("id") final long id);

    /**
     * @param categoriasId List<Long>
     * @return List<Categoria>
     */
    @Query("FROM Categoria categoria WHERE categoria.id IN :categoriasId")
    List<Categoria> findByCategoriasId(@Param("categoriasId") final List<Long> categoriasId);

    /**
     * @param pageable Pageable
     * @return Page<Categoria>
     */
    @Query("SELECT categoria FROM Categoria categoria " +
            "   WHERE categoria.id IN " +
            "       (" +
            "           SELECT categoriaFornecedor.categoria.id FROM CategoriaFornecedor categoriaFornecedor WHERE (categoriaFornecedor.categoria.id = categoria.id)" +
            "       )")
    Page<Categoria> listByFiltersWithFornecedores(final Pageable pageable);
}
