package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.cadastros.TipoDocumento;
import br.org.pti.compras.domain.repository.ITipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.org.pti.compras.application.resource.Roles.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({TIPO_DOCUMENTO_MAPPING_RESOURCE, "/sistema/" + TIPO_DOCUMENTO_MAPPING_RESOURCE})
public class TipoDocumentoResource {

    private final ITipoDocumentoRepository tipoDocumentoRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + TIPO_DOCUMENTO_GET_ROLE + "' , '" + ADMINISTRADOR + "')")
    public Page<TipoDocumento> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
        return this.tipoDocumentoRepository.listByFilters(defaultFilter, ativoFilter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + TIPO_DOCUMENTO_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Optional<TipoDocumento> findById(@PathVariable final long id) {
        return this.tipoDocumentoRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + TIPO_DOCUMENTO_POST_ROLE + "', '" + ADMINISTRADOR + "')")
    public TipoDocumento save(@RequestBody final TipoDocumento tipoDocumento) {
        return this.tipoDocumentoRepository.save(tipoDocumento);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + TIPO_DOCUMENTO_PUT_ROLE + "' , '" + ADMINISTRADOR + "')")
    public TipoDocumento save(@PathVariable final long id, @RequestBody final TipoDocumento tipoDocumento) {
        tipoDocumento.setId(id);
        return this.tipoDocumentoRepository.save(tipoDocumento);
    }

    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + TIPO_DOCUMENTO_PUT_ACTIVATE_ROLE + "', '" + ADMINISTRADOR + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        this.tipoDocumentoRepository.updateAtivo(id);
        return this.tipoDocumentoRepository.findById(id).get().getAtivo();
    }

}
