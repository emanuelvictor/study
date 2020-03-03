package br.org.pti.api.functional.inventario.domain.repository;

import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.Executor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface IExecutorRepository extends JpaRepository<Executor, Long> {

    /**
     * @param centroCustoInventarioId
     * @return
     */
    @Query(" FROM Executor executor WHERE ((executor.avulso IS NULL OR executor.avulso IS FALSE) AND  executor.centroCustoInventario.id = :centroCustoInventarioId )")
    List<Executor> findExecutoresByCentroCustoInventarioId(@Param("centroCustoInventarioId") final long centroCustoInventarioId);

    /**
     * @param centroCustoInventarioId
     * @return
     */
    @Query(" FROM Executor executor WHERE (executor.avulso IS TRUE AND executor.centroCustoInventario.id = :centroCustoInventarioId )")
    List<Executor> findExecutoresAvulsosByCentroCustoInventarioId(@Param("centroCustoInventarioId") final long centroCustoInventarioId);

    /**
     * @param usuarioId
     * @return
     */
    Set<Executor> findByUsuarioId(final long usuarioId);

}
