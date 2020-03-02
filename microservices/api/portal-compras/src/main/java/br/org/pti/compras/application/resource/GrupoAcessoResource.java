package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.configuracao.GrupoAcesso;
import br.org.pti.compras.domain.service.GrupoAcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.org.pti.compras.application.resource.Roles.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({GRUPO_ACESSO_MAPPING_RESOURCE, "/sistema/" + GRUPO_ACESSO_MAPPING_RESOURCE})
public class GrupoAcessoResource {

    private final GrupoAcessoService grupoAcessoService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Page<GrupoAcesso> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.grupoAcessoService.listByFilters(defaultFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Optional<GrupoAcesso> findById(@PathVariable final long id) {
        return this.grupoAcessoService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_POST_ROLE + "', '" + ADMINISTRADOR + "')")
    public GrupoAcesso save(@RequestBody final GrupoAcesso grupoAcesso) {
        grupoAcesso.getGruposAcessoPermissoes().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setGrupoAcesso(grupoAcesso)
        );
        return this.grupoAcessoService.save(grupoAcesso);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_PUT_ROLE + "', '" + ADMINISTRADOR + "')")
    public GrupoAcesso save(@PathVariable final long id, @RequestBody final GrupoAcesso grupoAcesso) {
        grupoAcesso.getGruposAcessoPermissoes().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setGrupoAcesso(grupoAcesso)
        );
        return this.grupoAcessoService.save(id, grupoAcesso);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('" + GRUPO_ACESSO_DELETE_ROLE + "', '" + ADMINISTRADOR + "')")
    public Boolean delete(@PathVariable final long id) {
        this.grupoAcessoService.delete(id);
        return true;
    }

}
