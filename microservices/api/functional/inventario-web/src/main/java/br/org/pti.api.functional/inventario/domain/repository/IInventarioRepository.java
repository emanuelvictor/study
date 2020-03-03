package br.org.pti.inventario.domain.repository;

import br.org.pti.inventario.domain.entity.patrimonio.inventario.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Long> {

    /**
     * @param filter    String
     * @param usuarioId long
     * @param pageable  Pageable
     * @return Page<Inventario>
     */
    @Query("FROM Inventario inventario " +
            "   WHERE " +
            "   (" +
            "       ( " +
            "           (FILTER(:filter, inventario.nome ) = TRUE)  " +
            "       ) " +
            "       AND " +
            "       (" +
            "           ((:usuarioId > 0L) AND " +
            "           (" +
            "               :usuarioId IN (" +
            "                   SELECT executor.usuario.id FROM Executor executor WHERE executor.centroCustoInventario.inventario.id = inventario.id" +
            "               )" +
            "               OR" +
            "               :usuarioId IN (" +
            "                   SELECT centroCusto.gestor.id FROM CentroCusto centroCusto WHERE " +
            "                   (" +
            "                       centroCusto.id IN " +
            "                       (" +
            "                           SELECT centroCustoInventario.centroCusto.id FROM CentroCustoInventario centroCustoInventario WHERE centroCustoInventario.inventario.id = inventario.id" +
            "                       )" +
            "                   )" +
            "               )" +
            "           ))" +
            "           " +
            "       ) OR (:usuarioId = 0L)" +
            "   )"
    )
    Page<Inventario> listByFilters(@Param("filter") final String filter,
                                   @Param("usuarioId") final long usuarioId,
                                   final Pageable pageable);

}
