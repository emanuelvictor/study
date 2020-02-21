package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.cadastros.Categoria;
import br.org.pti.compras.domain.repository.ICategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.org.pti.compras.application.resource.Roles.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({CATEGORIA_MAPPING_RESOURCE, "/sistema/" + CATEGORIA_MAPPING_RESOURCE})
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
    @PreAuthorize("hasAnyAuthority('" + CATEGORIA_POST_ROLE + "', '" + ADMINISTRADOR + "')")
    public Categoria save(@RequestBody final Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + CATEGORIA_PUT_ROLE + "', '" + ADMINISTRADOR + "')")
    public Categoria save(@PathVariable final long id, @RequestBody final Categoria categoria) {
        categoria.setId(id);
        return this.categoriaRepository.save(categoria);
    }

    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + CATEGORIA_PUT_ACTIVATE_ROLE + "', '" + ADMINISTRADOR + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        this.categoriaRepository.updateAtivo(id);
        return this.categoriaRepository.findById(id).orElseGet(Categoria::new).getAtivo();
    }

}
