package br.org.pti.inventario.domain.repository;

import br.org.pti.inventario.domain.entity.patrimonio.Patrimonio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Repository
public interface IPatrimonioRepository extends JpaRepository<Patrimonio, Long> {

    /**
     * @param codigoBase
     * @param nome
     * @param numero
     * @return
     */
    Optional<Patrimonio> findByCodigoBaseAndItemAndNumero(final String codigoBase, final String nome, final String numero);

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           (" +
            "               centroCusto.codigo = :centroCustoCodigo" +
            "           ) OR :centroCustoCodigo IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoPatrimonioFilter, patrimonio.item, patrimonio.codigoBase, patrimonio.descricao, patrimonio.complemento, patrimonio.observacao, patrimonio.capacidade, patrimonio.departamento, patrimonio.numeroSerie, patrimonio.modelo, patrimonio.marca) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoLocalizacaoFilter, localizacao.recno, localizacao.codigo, localizacao.descricao) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           (:numeroPlaquetaFilter IS NOT NULL AND patrimonio.numero = :numeroPlaquetaFilter) OR (:numeroPlaquetaFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "          centroCustoAnterior IS NOT NULL " +
            "       )" +
            "       AND " +
            "       (" +
            "           patrimonio.sobraFisica IS FALSE" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listNovosPatrimoniosByCentroCustoCodigoAndFilters(@Param("centroCustoCodigo") final String centroCustoCodigo,
                                                                       @Param("descricaoPatrimonioFilter") final String descricaoPatrimonioFilter,
                                                                       @Param("descricaoLocalizacaoFilter") final String descricaoLocalizacaoFilter,
                                                                       @Param("numeroPlaquetaFilter") final String numeroPlaquetaFilter,
                                                                       final Pageable pageable);

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           (" +
            "               centroCusto.codigo = :centroCustoCodigo" +
            "           ) OR :centroCustoCodigo IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoPatrimonioFilter, patrimonio.item, patrimonio.codigoBase, patrimonio.descricao, patrimonio.complemento, patrimonio.observacao, patrimonio.capacidade, patrimonio.departamento, patrimonio.numeroSerie, patrimonio.modelo, patrimonio.marca) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoLocalizacaoFilter, localizacao.recno, localizacao.codigo, localizacao.descricao) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           (:numeroPlaquetaFilter IS NOT NULL AND patrimonio.numero = :numeroPlaquetaFilter) OR (:numeroPlaquetaFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           patrimonio.sobraFisica IS TRUE" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listSobrasFisicasByCentroCustoCodigoAndFilters(@Param("centroCustoCodigo") final String centroCustoCodigo,
                                                                    @Param("descricaoPatrimonioFilter") final String descricaoPatrimonioFilter,
                                                                    @Param("descricaoLocalizacaoFilter") final String descricaoLocalizacaoFilter,
                                                                    @Param("numeroPlaquetaFilter") final String numeroPlaquetaFilter, final Pageable pageable);


    /**
     * @param centroCustoInventarioId
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           centroCustoInventario.id = :centroCustoInventarioId" +
            "       )" +
            "       AND " +
            "       (" +
            "           patrimonio.sobraFisica IS TRUE" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listSobrasFisicasByCentroCustoInventarioId(@Param("centroCustoInventarioId") final long centroCustoInventarioId, final Pageable pageable);


    /**
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param centroCustoCodigoFilter
     * @param encontradoFilter
     * @param sobraFisicaFilter
     * @param transferidoFilter
     * @param centrosCustosCodigo
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           (" +
            "               :centrosCustosCodigo IS NOT NULL AND centroCusto.codigo IN :centrosCustosCodigo" +
            "           ) OR :centrosCustosCodigo IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           (:centroCustoCodigoFilter IS NOT NULL AND (:centroCustoCodigoFilter = centroCusto.codigo AND centroCustoAnterior.codigo IS NULL) OR (centroCustoAnterior.codigo IS NOT NULL AND :centroCustoCodigoFilter = centroCustoAnterior.codigo AND patrimonio.encontrado = true)) OR :centroCustoCodigoFilter IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoPatrimonioFilter, patrimonio.item, patrimonio.descricao, patrimonio.complemento, patrimonio.observacao, patrimonio.capacidade, patrimonio.departamento, patrimonio.numeroSerie, patrimonio.modelo, patrimonio.marca) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:descricaoLocalizacaoFilter, localizacao.codigo, localizacao.descricao, localizacaoAnterior.codigo, localizacaoAnterior.descricao) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           (:numeroPlaquetaFilter IS NOT NULL AND patrimonio.numero = :numeroPlaquetaFilter) OR (:numeroPlaquetaFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :encontradoFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   patrimonio.encontrado = :encontradoFilter" +
            "               )" +
            "           ) " +
            "           OR (:encontradoFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :sobraFisicaFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   patrimonio.sobraFisica = :sobraFisicaFilter" +
            "               )" +
            "           ) " +
            "           OR (:sobraFisicaFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :transferidoFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   (" +
            "                       :transferidoFilter IS TRUE AND (patrimonio.centroCustoAnterior IS NOT NULL OR patrimonio.localizacaoAnterior IS NOT NULL)" +
            "                   )" +
            "                   OR" +
            "                   (" +
            "                       :transferidoFilter IS FALSE AND (patrimonio.centroCustoAnterior IS NULL AND patrimonio.localizacaoAnterior IS NULL)" +
            "                   )" +
            "               )" +
            "           ) " +
            "           OR (:transferidoFilter IS NULL)" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listAllPatrimoniosByCentroCustoCodigoAndFilters(@Param("descricaoPatrimonioFilter") final String descricaoPatrimonioFilter,
                                                                     @Param("descricaoLocalizacaoFilter") final String descricaoLocalizacaoFilter,
                                                                     @Param("numeroPlaquetaFilter") final String numeroPlaquetaFilter,
                                                                     @Param("centroCustoCodigoFilter") final String centroCustoCodigoFilter,
                                                                     @Param("encontradoFilter") final Boolean encontradoFilter,
                                                                     @Param("sobraFisicaFilter") final Boolean sobraFisicaFilter,
                                                                     @Param("transferidoFilter") final Boolean transferidoFilter,
                                                                     @Param("centrosCustosCodigo") final Set<String> centrosCustosCodigo,
                                                                     final Pageable pageable);

    /**
     * @param defaultFilter
     * @param centroCustoCodigoFilter
     * @param encontradoFilter
     * @param sobraFisicaFilter
     * @param transferidoFilter
     * @param centrosCustosCodigo
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           (" +
            "               :centrosCustosCodigo IS NOT NULL AND centroCusto.codigo IN :centrosCustosCodigo" +
            "           ) OR :centrosCustosCodigo IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           (:centroCustoCodigoFilter IS NOT NULL AND (:centroCustoCodigoFilter = centroCusto.codigo AND centroCustoAnterior.codigo IS NULL) OR (centroCustoAnterior.codigo IS NOT NULL AND :centroCustoCodigoFilter = centroCustoAnterior.codigo AND patrimonio.encontrado = true)) OR :centroCustoCodigoFilter IS NULL" +
            "       )" +
            "       AND " +
            "       (" +
            "           FILTER(:defaultFilter, patrimonio.numero, localizacao.codigo, localizacao.descricao, localizacaoAnterior.codigo, localizacaoAnterior.descricao, patrimonio.item, patrimonio.descricao, patrimonio.complemento, patrimonio.observacao, patrimonio.capacidade, patrimonio.departamento, patrimonio.numeroSerie, patrimonio.modelo, patrimonio.marca) = TRUE" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :encontradoFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   patrimonio.encontrado = :encontradoFilter" +
            "               )" +
            "           ) " +
            "           OR (:encontradoFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :sobraFisicaFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   patrimonio.sobraFisica = :sobraFisicaFilter" +
            "               )" +
            "           ) " +
            "           OR (:sobraFisicaFilter IS NULL)" +
            "       )" +
            "       AND " +
            "       (" +
            "           (" +
            "               :transferidoFilter IS NOT NULL " +
            "               AND " +
            "               (" +
            "                   (" +
            "                       :transferidoFilter IS TRUE AND (patrimonio.centroCustoAnterior IS NOT NULL OR patrimonio.localizacaoAnterior IS NOT NULL)" +
            "                   )" +
            "                   OR" +
            "                   (" +
            "                       :transferidoFilter IS FALSE AND (patrimonio.centroCustoAnterior IS NULL AND patrimonio.localizacaoAnterior IS NULL)" +
            "                   )" +
            "               )" +
            "           ) " +
            "           OR (:transferidoFilter IS NULL)" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listAllPatrimoniosByCentroCustoCodigoAndFilters(@Param("defaultFilter") final String defaultFilter,
                                                                     @Param("centroCustoCodigoFilter") final String centroCustoCodigoFilter,
                                                                     @Param("encontradoFilter") final Boolean encontradoFilter,
                                                                     @Param("sobraFisicaFilter") final Boolean sobraFisicaFilter,
                                                                     @Param("transferidoFilter") final Boolean transferidoFilter,
                                                                     @Param("centrosCustosCodigo") final Set<String> centrosCustosCodigo,
                                                                     final Pageable pageable);

    /**
     * @param centroCustoInventarioId
     * @param pageable
     * @return
     */
    @Query("SELECT new Patrimonio(" +
            "       patrimonio.id," +
            "       patrimonio.numero, " +
            "       patrimonio.item," +
            "       patrimonio.codigoBase," +
            "       patrimonio.descricao, " +
            "       patrimonio.complemento," +
            "       patrimonio.observacao," +
            "       patrimonio.capacidade," +
            "       patrimonio.departamento," +
            "       patrimonio.encontrado," +
            "       patrimonio.numeroSerie," +
            "       patrimonio.modelo," +
            "       patrimonio.marca," +
            "       patrimonio.sobraFisica," +
            "       localizacao," +
            "       localizacaoAnterior," +
            "       centroCustoAnterior," +
            "       centroCustoInventario" +
            "   ) " +
            "   FROM Patrimonio patrimonio " +
            "       LEFT OUTER JOIN Localizacao localizacao ON localizacao.id = patrimonio.localizacao.id " +
            "       LEFT OUTER JOIN Localizacao localizacaoAnterior ON localizacaoAnterior.id = patrimonio.localizacaoAnterior.id " +
            "       LEFT OUTER JOIN CentroCustoInventario centroCustoInventario ON centroCustoInventario.id = patrimonio.centroCustoInventario.id " +
            "       LEFT OUTER JOIN CentroCusto centroCusto ON centroCusto.id = centroCustoInventario.centroCusto.id " +
            "       LEFT OUTER JOIN CentroCusto centroCustoAnterior ON centroCustoAnterior.id = patrimonio.centroCustoAnterior.id " +
            "   WHERE" +
            "   (" +
            "       (" +
            "           centroCustoInventario.id = :centroCustoInventarioId" +
            "       )" +
            "   )"
    )
    Page<Patrimonio> listAllPatrimoniosByCentroCustoInventarioId(@Param("centroCustoInventarioId") final long centroCustoInventarioId, final Pageable pageable);
}
