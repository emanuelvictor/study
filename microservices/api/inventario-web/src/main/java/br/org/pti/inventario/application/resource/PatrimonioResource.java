package br.org.pti.inventario.application.resource;

import br.org.pti.inventario.domain.entity.patrimonio.Localizacao;
import br.org.pti.inventario.domain.entity.patrimonio.Patrimonio;
import br.org.pti.inventario.domain.entity.patrimonio.dto.PatrimonioDTO;
import br.org.pti.inventario.domain.service.PatrimonioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static br.org.pti.inventario.application.resource.Roles.*;

/**
 * RESTFul de Patrimônios
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({PATRIMONIO_MAPPING_RESOURCE, "/sistema/" + PATRIMONIO_MAPPING_RESOURCE})
public class PatrimonioResource {

    /**
     *
     */
    private final PatrimonioService patrimonioService;

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}/by-id")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Optional<Patrimonio> findById(@PathVariable final Long id) {
        return patrimonioService.findById(id);
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param pageable
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Page<PatrimonioDTO> listPatrimoniosByCentroCustoCodigoAndFilters(@NotNull final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        final Page<PatrimonioDTO> patrimonios = this.patrimonioService.listPatrimoniosByCentroCustoCodigoAndFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, numeroPlaquetaFilter, pageable);

        patrimonios.getContent().forEach(patrimonio -> {
            if (patrimonio.getCentroCustoInventario() != null) {
                patrimonio.getCentroCustoInventario().setExecutores(null);
                patrimonio.getCentroCustoInventario().getInventario().setCentrosCusto(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setExecutores(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setCentrosCusto(null);
                if (patrimonio.getCentroCustoAnterior() != null) {
                    patrimonio.getCentroCustoAnterior().getGestor().setCentrosCusto(null);
                    patrimonio.getCentroCustoAnterior().getGestor().setExecutores(null);
                }
            }
        });

        return patrimonios;
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param pageable
     * @return
     */
    @GetMapping("novos")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Page<Patrimonio> listNovosPatrimoniosByCentroCustoCodigoAndFilters(@NotNull final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        final Page<Patrimonio> patrimonios = this.patrimonioService.listNovosPatrimoniosByCentroCustoCodigoAndFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, numeroPlaquetaFilter, pageable);

        patrimonios.getContent().forEach(patrimonio -> {
            if (patrimonio.getCentroCustoInventario() != null) {
                patrimonio.getCentroCustoInventario().setExecutores(null);
                patrimonio.getCentroCustoInventario().getInventario().setCentrosCusto(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setExecutores(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setCentrosCusto(null);
                if (patrimonio.getCentroCustoAnterior() != null) {
                    patrimonio.getCentroCustoAnterior().getGestor().setCentrosCusto(null);
                    patrimonio.getCentroCustoAnterior().getGestor().setExecutores(null);
                }
            }
        });

        //
        return patrimonios;
    }

    /**
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param centroCustoCodigoFilter
     * @param encontradoFilter
     * @param sobraFisicaFilter
     * @param transferidoFilter
     * @param pageable
     * @return
     */
    @GetMapping("all")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Page<Patrimonio> listAllPatrimoniosByCentroCustoCodigoAndFilters(final String defaultFilter,
                                                                            final String descricaoPatrimonioFilter,
                                                                            final String descricaoLocalizacaoFilter,
                                                                            final String numeroPlaquetaFilter,
                                                                            final String centroCustoCodigoFilter,
                                                                            final Boolean encontradoFilter,
                                                                            final Boolean sobraFisicaFilter,
                                                                            final Boolean transferidoFilter,
                                                                            final Pageable pageable) {

        final Page<Patrimonio> patrimonios;
        if (defaultFilter != null)
            patrimonios = this.patrimonioService.listAllPatrimoniosByCentroCustoCodigoAndFilters(
                    defaultFilter,
                    centroCustoCodigoFilter,
                    encontradoFilter,
                    sobraFisicaFilter,
                    transferidoFilter,
                    pageable);
        else
            patrimonios = this.patrimonioService.listAllPatrimoniosByCentroCustoCodigoAndFilters(
                    descricaoPatrimonioFilter,
                    descricaoLocalizacaoFilter,
                    numeroPlaquetaFilter,
                    centroCustoCodigoFilter,
                    encontradoFilter,
                    sobraFisicaFilter,
                    transferidoFilter,
                    pageable);

        patrimonios.getContent().forEach(patrimonio -> {
            if (patrimonio.getCentroCustoInventario() != null) {
                patrimonio.getCentroCustoInventario().setExecutores(null);
                patrimonio.getCentroCustoInventario().getInventario().setCentrosCusto(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setExecutores(null);
                patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setCentrosCusto(null);
                if (patrimonio.getCentroCustoAnterior() != null) {
                    patrimonio.getCentroCustoAnterior().getGestor().setCentrosCusto(null);
                    patrimonio.getCentroCustoAnterior().getGestor().setExecutores(null);
                }
            }
        });

        //
        return patrimonios;
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param pageable
     * @return
     */
    @GetMapping("sobras-fisicas")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Page<Patrimonio> listSobrasFisicasByCentroCustoCodigoAndFilters(@NotNull final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        final Page<Patrimonio> patrimonios = this.patrimonioService.listSobrasFisicasByCentroCustoCodigoAndFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, numeroPlaquetaFilter, pageable);

        patrimonios.getContent().forEach(patrimonio -> {
            patrimonio.getCentroCustoInventario().setExecutores(null);
            patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setExecutores(null);
            patrimonio.getCentroCustoInventario().getCentroCusto().getGestor().setCentrosCusto(null);
        });

        //
        return patrimonios;
    }

    /**
     * @param numero
     * @return
     */
    @GetMapping("{numero}")
    Page<PatrimonioDTO> findByNumero(@PathVariable final String numero) {
        return this.patrimonioService.findByNumero(numero);
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoLocalizacaoFilter
     * @return
     */
    @GetMapping("localizacoes")
    public Set<Localizacao> listByLocalizacoesFilters(@NotNull final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter) {
        return this.patrimonioService.listByLocalizacoesFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter);
    }

    /**
     * @param patrimonio Patrimonio
     * @return Patrimonio
     */
    @PostMapping("/sobra-fisica")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Patrimonio inserirSobraFisica(@RequestBody final Patrimonio patrimonio) {
        return this.patrimonioService.inserirSobraFisica(patrimonio);
    }

    /**
     * @param id
     * @param patrimonio
     * @return
     */
    @PostMapping("sobra-fisica/{id}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Patrimonio alterarSobraFisica(@PathVariable final long id, @RequestBody final Patrimonio patrimonio) {
        patrimonio.setId(id);
        return this.patrimonioService.alterarSobraFisica(patrimonio);
    }

    /**
     * @param patrimonio Patrimonio
     * @return Patrimonio
     */
    @PostMapping("/novo-patrimonio")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Patrimonio inserirNovoPatrimonio(@RequestBody final Patrimonio patrimonio) {
        return this.patrimonioService.inserirNovoPatrimonio(patrimonio);
    }

    /**
     * @param patrimonio Patrimonio
     * @return Patrimonio
     */
    @PostMapping("/encontrar")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Patrimonio encontrar(@RequestBody final Patrimonio patrimonio) {
        return this.patrimonioService.encontrar(patrimonio);
    }

    /**
     * @param id
     * @param localizacao
     * @return
     */
    @PostMapping("{id}/alterar-localizacao")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Patrimonio alterarLocalizacao(@PathVariable final long id, @RequestBody final Localizacao localizacao) {
        return this.patrimonioService.alterarLocalizacao(id, localizacao);
    }

    /**
     * @param id Long
     * @return Boolean
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_EXECUTE_ROLE + "', '" + ROOT + "')")
    public Boolean delete(@PathVariable final long id) {
        this.patrimonioService.delete(id);
        return true;
    }

    /**
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<byte[]> export(final String defaultFilter,
                                         final String centroCustoCodigoFilter,
                                         final Boolean encontradoFilter,
                                         final Boolean sobraFisicaFilter,
                                         final Boolean transferidoFilter,
                                         final Pageable pageable) throws IOException {

        final byte[] csvFileByteArray = this.patrimonioService.export(
                defaultFilter,
                centroCustoCodigoFilter,
                encontradoFilter,
                sobraFisicaFilter,
                transferidoFilter,
                pageable);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "Arquivo de Exportação" + ".csv" + "\"")
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvFileByteArray);
    }

}
