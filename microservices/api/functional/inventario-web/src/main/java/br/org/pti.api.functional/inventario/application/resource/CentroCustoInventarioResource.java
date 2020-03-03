package br.org.pti.inventario.application.resource;

import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventarioStatus;
import br.org.pti.inventario.domain.repository.ICentroCustoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static br.org.pti.inventario.application.resource.Roles.*;

/**
 * RESTFul de Centros de Custo
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({CENTRO_CUSTO_INVENTARIO_MAPPING_RESOURCE, "/sistema/" + CENTRO_CUSTO_INVENTARIO_MAPPING_RESOURCE})
public class CentroCustoInventarioResource {

    /**
     *
     */
    private final ICentroCustoInventarioRepository centroCustoInventarioRepository;

    /**
     * @param centroCustoInventario
     * @return
     */
    @Transactional
    @PutMapping("/{id}/extender-data-termino")
    @PreAuthorize("hasAnyAuthority('" + INVENTARIO_PUT_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario extenderDataTermino(final @PathVariable("id") long id, final @RequestBody CentroCustoInventario centroCustoInventario) {
        final CentroCustoInventario centroCustoInventario1 = this.centroCustoInventarioRepository.findById(id).orElseThrow();
        centroCustoInventario1.setDataTerminoExtendida(centroCustoInventario.getDataTerminoExtendida());
        centroCustoInventario1.setStatus(CentroCustoInventarioStatus.EM_EXECUCAO);
        return this.centroCustoInventarioRepository.save(centroCustoInventario1);
    }

}
