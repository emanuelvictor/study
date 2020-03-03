package br.org.pti.api.functional.inventario.domain.repository;

import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface ICentroCustoInventarioRepository extends JpaRepository<CentroCustoInventario, Long> {

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @Query(
            " FROM CentroCustoInventario centroCustoInventario WHERE (" +
                    "       centroCustoInventario.inventario.id = :inventarioId AND centroCustoInventario.centroCusto.codigo = :centroCustoCodigo " +
                    "   )"
    )
    CentroCustoInventario findByInventarioIdAndCentroCustoCodigo(@Param("inventarioId") final long inventarioId, @Param("centroCustoCodigo") final String centroCustoCodigo);

    /**
     * @param hoje
     * @return
     */
    @Query("FROM CentroCustoInventario centroCustoInventario WHERE centroCustoInventario.inventario.dataTermino < :hoje")
    List<CentroCustoInventario> listVencidos(@Param("hoje") final LocalDate hoje);
}
