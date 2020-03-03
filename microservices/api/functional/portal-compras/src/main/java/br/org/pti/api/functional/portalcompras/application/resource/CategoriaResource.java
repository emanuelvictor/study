package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.ICategoriaRepository;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Categoria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping({Roles.CATEGORIA_MAPPING_RESOURCE, "/sistema/" + Roles.CATEGORIA_MAPPING_RESOURCE})
public class CategoriaResource {


    private final ICategoriaRepository categoriaRepository;

    @GetMapping
    public Page<Categoria> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
        return this.categoriaRepository.listByFilters(defaultFilter, ativoFilter, pageable);
    }

    @GetMapping("/withFornecedores")
    public Page<Categoria> listByFiltersWithFornecedores(final Pageable pageable) {
        return this.categoriaRepository.listByFiltersWithFornecedores(pageable);
    }

    @GetMapping("/{id}")
    public Optional<Categoria> findById(@PathVariable final long id) {
        return this.categoriaRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.CATEGORIA_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Categoria save(@RequestBody final Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.CATEGORIA_PUT_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Categoria save(@PathVariable final long id, @RequestBody final Categoria categoria) {
        categoria.setId(id);
        return this.categoriaRepository.save(categoria);
    }

    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + Roles.CATEGORIA_PUT_ACTIVATE_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        this.categoriaRepository.updateAtivo(id);
        return this.categoriaRepository.findById(id).orElseGet(Categoria::new).getAtivo();
    }

}
