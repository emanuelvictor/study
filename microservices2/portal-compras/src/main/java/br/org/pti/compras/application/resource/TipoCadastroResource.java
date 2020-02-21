package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.cadastros.TipoCadastro;
import br.org.pti.compras.domain.repository.ITipoCadastroRepository;
import br.org.pti.compras.domain.repository.ITipoCadastroTipoDocumentoRepository;
import br.org.pti.compras.domain.service.TipoCadastroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static br.org.pti.compras.application.resource.Roles.*;

@RestController
@RequiredArgsConstructor
@RequestMapping({TIPO_CADASTRO_MAPPING_RESOURCE, "/sistema/" + TIPO_CADASTRO_MAPPING_RESOURCE})
public class TipoCadastroResource {

    private final ITipoCadastroRepository tipoCadastroRepository;

    private final ITipoCadastroTipoDocumentoRepository tipoCadastroTipoDocumentoRepository;

    private final TipoCadastroService tipoCadastroService;

    @GetMapping
    public Page<TipoCadastro> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
        return this.tipoCadastroRepository.listByFilters(defaultFilter, ativoFilter, pageable);
    }

    @GetMapping("/{id}")
    public Optional<TipoCadastro> findById(@PathVariable final long id) {
        return this.tipoCadastroRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + TIPO_CADASTRO_POST_ROLE + "', '" + ADMINISTRADOR + "')")
    public TipoCadastro save(@RequestBody final TipoCadastro tipoCadastro) {
        tipoCadastro.getDocumentos().forEach(documento ->
                documento.setTipoCadastro(tipoCadastro)
        );
        return this.tipoCadastroService.save(tipoCadastro);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + TIPO_CADASTRO_PUT_ROLE + "' , '" + ADMINISTRADOR + "')")
    public TipoCadastro save(@PathVariable final long id, @RequestBody final TipoCadastro tipoCadastro) {
        tipoCadastro.getDocumentos().forEach(documento ->
                documento.setTipoCadastro(tipoCadastro)
        );
        return this.tipoCadastroService.save(id, tipoCadastro);
    }

    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + TIPO_CADASTRO_PUT_ACTIVATE_ROLE + "', '" + ADMINISTRADOR + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        this.tipoCadastroRepository.updateAtivo(id);
        return this.tipoCadastroRepository.findById(id).get().getAtivo();
    }

}
