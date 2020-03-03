package br.org.pti.api.functional.inventario.application.resource;

import br.org.pti.api.functional.inventario.domain.entity.configuracao.GrupoAcesso;
import br.org.pti.api.functional.inventario.domain.service.GrupoAcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.org.pti.api.functional.inventario.application.resource.Roles.*;

/**
 * RESTFul de Grupos de Acesso
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({GRUPO_ACESSO_MAPPING_RESOURCE, "/sistema/" + GRUPO_ACESSO_MAPPING_RESOURCE})
public class GrupoAcessoResource {

    /**
     *
     */
    private final GrupoAcessoService grupoAcessoService;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<GrupoAcesso>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_GET_ROLE + "', '" + ROOT + "')")
    public Page<GrupoAcesso> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.grupoAcessoService.listByFilters(defaultFilter, pageable);
    }

    /**
     * @param id long
     * @return Optional<GrupoAcesso>
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_GET_ROLE + "', '" + ROOT + "')")
    public Optional<GrupoAcesso> findById(@PathVariable final long id) {
        return this.grupoAcessoService.findById(id);
    }

    /**
     * @param grupoAcesso GrupoAcesso
     * @return GrupoAcesso
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_POST_ROLE + "', '" + ROOT + "')")
    public GrupoAcesso save(@RequestBody final GrupoAcesso grupoAcesso) {
        grupoAcesso.getGruposAcessoPermissoes().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setGrupoAcesso(grupoAcesso)
        );
        return this.grupoAcessoService.save(grupoAcesso);
    }

    /**
     * @param id          long
     * @param grupoAcesso GrupoAcesso
     * @return GrupoAcesso
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_PUT_ROLE + "', '" + ROOT + "')")
    public GrupoAcesso save(@PathVariable final long id, @RequestBody final GrupoAcesso grupoAcesso) {
        grupoAcesso.getGruposAcessoPermissoes().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setGrupoAcesso(grupoAcesso)
        );
        return this.grupoAcessoService.save(id, grupoAcesso);
    }

    /**
     * @param id long
     * @return Boolean
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_DELETE_ROLE + "', '" + ROOT + "')")
    public Boolean delete(@PathVariable final long id) {
        this.grupoAcessoService.delete(id);
        return true;
    }

}
