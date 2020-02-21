package br.org.pti.inventario.application.resource;

import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.Inventario;
import br.org.pti.inventario.domain.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static br.org.pti.inventario.application.resource.Roles.*;

/**
 * RESTFul de Inventários
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({INVENTARIO_MAPPING_RESOURCE, "/sistema/" + INVENTARIO_MAPPING_RESOURCE})
public class InventarioResource {

    /**
     *
     */
    private final InventarioService inventarioService;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<Inventario>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public Page<Inventario> listByFilters(final String defaultFilter, final Pageable pageable) {

        final Page<Inventario> pageInventario = this.inventarioService.listByFilters(defaultFilter, pageable);

        pageInventario.forEach(this::removeRecursividade);

        return pageInventario;
    }

    /**
     * @param id long
     * @return Inventario
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public Inventario findById(@PathVariable final long id) {

        final Inventario inventario = this.inventarioService.findById(id);

        removeRecursividade(inventario);

        return inventario;
    }

    /**
     * @param inventario
     */
    private void removeRecursividade(final Inventario inventario) {
        // Remove objetos recursivos do front-end
        inventario.getCentrosCusto().forEach(a -> {
            a.getCentroCusto().getGestor().setCentrosCusto(null);
            a.getCentroCusto().getGestor().setExecutores(null);
            a.getExecutores().forEach(e -> {
                e.getUsuario().setCentrosCusto(null);
                e.getUsuario().setExecutores(null);
            });
        });
    }

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @GetMapping("/{inventarioId}/{centroCustoCodigo}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario findByInventarioIdAndCentroCustoCodigo(@PathVariable final long inventarioId, @PathVariable final String centroCustoCodigo) {
        return this.inventarioService.findByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);
    }

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @GetMapping("/{inventarioId}/{centroCustoCodigo}/finalizar")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario finalizar(@PathVariable final long inventarioId, @PathVariable final String centroCustoCodigo) {
        return this.inventarioService.finalizar(inventarioId, centroCustoCodigo);
    }

    /**
     * Rollback pra suprir as cabacices dos usuários
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @GetMapping("/{inventarioId}/{centroCustoCodigo}/desfinalizar")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario desfinalizar(@PathVariable final long inventarioId, @PathVariable final String centroCustoCodigo) {
        return this.inventarioService.desfinalizar(inventarioId, centroCustoCodigo);
    }

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @GetMapping(value = "/{inventarioId}/{centroCustoCodigo}/exportar", produces = "text/csv")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public ResponseEntity<byte[]> exportar(@PathVariable final long inventarioId, @PathVariable final String centroCustoCodigo) throws IOException {
        final byte[] csvFileByteArray = this.inventarioService.exportar(inventarioId, centroCustoCodigo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "Arquivo de Exportação - " + centroCustoCodigo + ".csv" + "\"")
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvFileByteArray);
    }

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    @GetMapping(value = "/{inventarioId}/{centroCustoCodigo}/exportar/sobras-fisicas", produces = "text/csv")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_GET_ROLE + "', '" + ROOT + "')")
    public ResponseEntity<byte[]> exportarSobrasFisicas(@PathVariable final long inventarioId, @PathVariable final String centroCustoCodigo) throws IOException {
        final byte[] csvFileByteArray = this.inventarioService.exportarSobrasFisicas(inventarioId, centroCustoCodigo);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "Arquivo de Exportação - " + centroCustoCodigo + ".csv" + "\"")
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvFileByteArray);
    }

    /**
     * @param inventario Inventario
     * @return Inventario
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_POST_ROLE + "', '" + ROOT + "')")
    public Inventario save(@RequestBody final Inventario inventario) {
        return this.inventarioService.save(inventario);
    }

    /**
     * @param id         long
     * @param inventario Inventario
     * @return Inventario
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_PUT_ROLE + "', '" + ROOT + "')")
    public Inventario save(@PathVariable final long id, @RequestBody final Inventario inventario) {

        inventario.setId(id);

        return this.inventarioService.save(inventario);
    }

    /**
     * @param id Long
     * @return Boolean
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_DELETE_ROLE + "', '" + ROOT + "')")
    public Boolean delete(@PathVariable final long id) {

        this.inventarioService.delete(id);

        return true;
    }

}
