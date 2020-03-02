package br.org.pti.inventario.application.resource;

import br.org.pti.inventario.application.context.ContextHolder;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventarioStatus;
import br.org.pti.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.inventario.domain.entity.pessoal.dto.CentroCustoDTO;
import br.org.pti.inventario.domain.entity.pessoal.dto.ColaboradorDTO;
import br.org.pti.inventario.domain.repository.ICentroCustoInventarioRepository;
import br.org.pti.inventario.domain.repository.IPatrimonioRepository;
import br.org.pti.inventario.domain.repository.feign.IPatrimonioFeignRepository;
import br.org.pti.inventario.domain.service.CentroCustoService;
import br.org.pti.inventario.domain.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
@RequestMapping({CENTRO_CUSTO_MAPPING_RESOURCE, "/sistema/" + CENTRO_CUSTO_MAPPING_RESOURCE})
public class CentroCustoResource {

    /**
     *
     */
    private final ColaboradorService colaboradorService;

    /**
     *
     */
    private final CentroCustoService centroCustoService;

    /**
     *
     */
    private final IPatrimonioRepository patrimonioRepository;

    /**
     *
     */
    private final IPatrimonioFeignRepository patrimonioFeignRepository;

    /**
     *
     */
    private final ICentroCustoInventarioRepository centroCustoInventarioRepository;

    /**
     * @param descricaoFilter {String}
     * @param pageable        Pageable
     * @return Page<CentroCusto></CentroCusto>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Page<CentroCustoDTO> listByFilters(final String descricaoFilter, final String centroCustoCodigoFilter, final Pageable pageable) {
        return this.centroCustoService.listByFilters(descricaoFilter, centroCustoCodigoFilter, pageable);
    }

    /**
     * @param codigo String
     * @return CentroCusto
     */
    @GetMapping("/{codigo}")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoDTO findByCodigo(final String codigo) {
        return this.centroCustoService.findByCodigo(codigo);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("{id}/by-id")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Optional<CentroCusto> findById(@PathVariable final Long id) {
        return centroCustoService.findById(id);
    }

    /**
     * @param centroCustoCodigo String
     * @return CentroCusto
     */
    @GetMapping("/{centroCustoCodigo}/colaboradores")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Page<ColaboradorDTO> listColaboradoresByCentroCustoCodigo(@PathVariable("centroCustoCodigo") final String centroCustoCodigo, final Pageable pageable) {
        return this.colaboradorService.listColaboradoresByCentroCustoCodigo(centroCustoCodigo, pageable);
    }


    /**
     * @param id long
     * @return CentroCustoInventario
     */
    @GetMapping("/centro-custo-inventario/{id}")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCusto findCentroCustoInventarioById(@PathVariable("id") final long id) {
        return this.centroCustoService.findCentroCustoInventarioById(id);
    }

    /**
     * @param id long
     * @return CentroCustoInventario
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}/{centroCustoCodigo}")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario findByInventarioIdAndCentroCustoCodigo(@PathVariable final long id, @PathVariable final String centroCustoCodigo) {
        final CentroCustoInventario centroCustoInventario = this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(id, centroCustoCodigo);
        centroCustoInventario.setExecutores(null);
        centroCustoInventario.getInventario().setCentrosCusto(null);
        return centroCustoInventario;
    }

    /**
     * @param id long
     * @return CentroCustoInventario
     */
    @GetMapping("/{id}/{centroCustoCodigo}/executar")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario executarCentroCustoInventario(@PathVariable final long id, @PathVariable final String centroCustoCodigo) {

        final CentroCustoInventario centroCustoInventario = this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(id, centroCustoCodigo);

        if (!(ContextHolder.getAuthenticatedUser().getIsPatrimonio() || ContextHolder.getAuthenticatedUser().isRoot()))
            Assert.isTrue(ContextHolder.getAuthenticatedUser().getId().equals(centroCustoInventario.getCentroCusto().getGestor().getId()), "Você não é gestor deste Centro de Custo");

        Assert.isTrue(!centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.APROVADO) /*&& !centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.EM_ANALISE)*/, "Você não pode executar esse inventário para esse centro de custo");

        centroCustoInventario.setStatus(CentroCustoInventarioStatus.EM_EXECUCAO);

        centroCustoInventarioRepository.save(centroCustoInventario);

        centroCustoInventario.setExecutores(null);
        centroCustoInventario.getInventario().setCentrosCusto(null);

        return centroCustoInventario;
    }

    /**
     * @param id long
     * @return CentroCustoInventario
     */
    @GetMapping("/{id}/{centroCustoCodigo}/to-analise")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario toAnaliseCentroCustoInventario(@PathVariable final long id, @PathVariable final String centroCustoCodigo) {

        final CentroCustoInventario centroCustoInventario = this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(id, centroCustoCodigo);

        final boolean auth;

        if (ContextHolder.getAuthenticatedUser().getIsPatrimonio() || ContextHolder.getAuthenticatedUser().isRoot()) {
            auth = true;
        } else if (centroCustoInventario.getExecutores().stream().anyMatch(executor -> executor.getUsuario().getId().equals(ContextHolder.getAuthenticatedUser().getId()))) {
            auth = true;
        } else
            auth = centroCustoInventario.getCentroCusto().getGestor().getId().equals(ContextHolder.getAuthenticatedUser().getId());

        Assert.isTrue(auth, "Você não pode submeter á análise esse inventário para esse centro de custo");

        Assert.isTrue(centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.EM_EXECUCAO), "Você não pode submeter á análise esse inventário para esse centro de custo");

        centroCustoInventario.setStatus(CentroCustoInventarioStatus.EM_ANALISE);

        centroCustoInventarioRepository.save(centroCustoInventario);

        centroCustoInventario.setExecutores(null);
        centroCustoInventario.getInventario().setCentrosCusto(null);

        return centroCustoInventario;
    }

    /**
     * @param id long
     * @return CentroCustoInventario
     */
    @GetMapping("/{id}/{centroCustoCodigo}/aprovar")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public CentroCustoInventario aprovarCentroCustoInventario(@PathVariable final long id, @PathVariable final String centroCustoCodigo) {

        final CentroCustoInventario centroCustoInventario = this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(id, centroCustoCodigo);

        final boolean auth;

        if (ContextHolder.getAuthenticatedUser().getIsPatrimonio() || ContextHolder.getAuthenticatedUser().isRoot()) {
            auth = true;
        } else
            auth = centroCustoInventario.getCentroCusto().getGestor().getId().equals(ContextHolder.getAuthenticatedUser().getId());

        Assert.isTrue(auth, "Você não pode aprovar esse inventário para esse centro de custo");

        Assert.isTrue(centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.EM_ANALISE), "Você não pode aprovar esse inventário para esse centro de custo");

        centroCustoInventario.setStatus(CentroCustoInventarioStatus.APROVADO);

        centroCustoInventarioRepository.save(centroCustoInventario);

        centroCustoInventario.setExecutores(null);
        centroCustoInventario.getInventario().setCentrosCusto(null);

        return centroCustoInventario;
    }

    /**
     * @param centroCustoCodigo
     * @return
     */
    @GetMapping("/{centroCustoCodigo}/has-inventariaveis")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public boolean hasInventariaveis(@PathVariable final String centroCustoCodigo) {
        return this.countPatrimoniosNaoInventariados(centroCustoCodigo) > 0;
    }

    /**
     * @return CentroCustoInventario
     */
    @Transactional(readOnly = true)
    @GetMapping("/{centroCustoCodigo}/inventariados")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Integer countPatrimoniosInventariados(@PathVariable final String centroCustoCodigo) {
//        return Long.valueOf(this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, null, false, null, null, null).getContent().size()).intValue();
        return Long.valueOf(this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, null, false, null, null, null).getContent().stream().filter(patrimonio ->
                patrimonio.getCentroCustoAnterior() == null || patrimonio.getCentroCustoAnterior().getCodigo().equals(centroCustoCodigo)
        ).count()).intValue();
    }

    /**
     * @return CentroCustoInventario
     */
    @Transactional(readOnly = true)
    @GetMapping("/{centroCustoCodigo}/nao-inventariados")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Integer countPatrimoniosNaoInventariados(@PathVariable final String centroCustoCodigo) {
        return this.patrimonioFeignRepository.listByFilters(centroCustoCodigo, null, null, new PageRequest(0, 20000000)).getContent().size() - this.countPatrimoniosInventariados(centroCustoCodigo);
    }

    /**
     * @return CentroCustoInventario
     */
    @Transactional(readOnly = true)
    @GetMapping("/{centroCustoCodigo}/encontrados")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Integer countPatrimoniosEncontrados(@PathVariable final String centroCustoCodigo) {
//        System.out.println(" ----------------------------- encontrado");
//        System.out.println(Long.valueOf(this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, true, false, null, null, null).getContent().size()).intValue());
//        System.out.println(Long.valueOf(this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, true, false, null, null, null).getContent().stream().filter(patrimonio ->
//                patrimonio.getCentroCustoAnterior() == null || patrimonio.getCentroCustoAnterior().getCodigo().equals(centroCustoCodigo)
//        ).count()).intValue());
        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, true, false, null, null, null).getContent().size();
    }

    /**
     * @return CentroCustoInventario
     */
    @Transactional(readOnly = true)
    @GetMapping("/{centroCustoCodigo}/nao-encontrados")
    @PreAuthorize("hasAnyAuthority('" + CENTRO_CUSTO_GET_ROLE + "', '" + ROOT + "')")
    public Integer countPatrimoniosNaoEncontrados(@PathVariable final String centroCustoCodigo) {
//        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, false, false, null, null, null).getContent().size();
        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(null, centroCustoCodigo, false, false, null, null, null).getContent().size();
    }
}
