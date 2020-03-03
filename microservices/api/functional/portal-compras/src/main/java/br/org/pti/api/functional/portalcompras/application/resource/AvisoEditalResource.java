package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.IAnexoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IAvisoEditalRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.ICategoriaAvisoEditalRepository;
import br.org.pti.api.functional.portalcompras.infrastructure.aid.Utils;
import br.org.pti.api.functional.portalcompras.application.resource.generic.AbstractArquivoResource;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Anexo;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.AvisoEdital;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Status;
import br.org.pti.api.functional.portalcompras.domain.repository.IAnexoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IAvisoEditalRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.ICategoriaAvisoEditalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static br.org.pti.api.functional.portalcompras.application.resource.Roles.*;
import static br.org.pti.api.functional.portalcompras.infrastructure.aid.Utils.getListFromArray;

@RestController
@RequestMapping({Roles.AVISO_EDITAL_MAPPING_RESOURCE, "/sistema/" + Roles.AVISO_EDITAL_MAPPING_RESOURCE})
public class AvisoEditalResource extends AbstractArquivoResource {

    /**
     *
     */
    private final IAvisoEditalRepository avisoEditalRepository;

    /**
     *
     */
    private final ICategoriaAvisoEditalRepository categoriaAvisoEditalRepository;

    /**
     * @param anexoRepository                {IAnexoRepository}
     * @param avisoEditalRepository          {IAvisoEditalRepository}
     * @param categoriaAvisoEditalRepository {ICategoriaAvisoEditalRepository}
     */
    @Autowired
    public AvisoEditalResource(final IAnexoRepository anexoRepository,
                               final IAvisoEditalRepository avisoEditalRepository,
                               final ICategoriaAvisoEditalRepository categoriaAvisoEditalRepository) {
        super(anexoRepository, null);
        this.avisoEditalRepository = avisoEditalRepository;
        this.categoriaAvisoEditalRepository = categoriaAvisoEditalRepository;
    }

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('" + AVISO_EDITAL_GET_ROLE + "' , '" + ADMINISTRADOR + "')")
    public Page<AvisoEdital> listByFilters(final String defaultFilter, final Long[] categoriasFilter, final Status status,
                                           final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio, final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim, final Pageable pageable) {


        LocalDate min = null;
        LocalDate max = null;

        if (dataInicio != null) {
            min = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (dataFim != null) {
            max = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }


        final Page<AvisoEdital> avisoEditais = this.avisoEditalRepository.listByFilters(defaultFilter, Utils.getListFromArray(categoriasFilter), status, min, max, pageable);

        avisoEditais.forEach(avisoEdital ->
                avisoEdital.setCategoriasAvisosEditais(this.categoriaAvisoEditalRepository.findByAvisoEditalIdOrderByCategoriaNome(avisoEdital.getId()))
        );

        return avisoEditais;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('" + AVISO_EDITAL_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Optional<AvisoEdital> findById(@PathVariable final long id) {
        return this.avisoEditalRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public AvisoEdital save(@RequestBody final AvisoEdital avisoEdital) {
        avisoEdital.getCategoriasAvisosEditais().forEach(categoriaAvisoEdital ->
                categoriaAvisoEdital.setAvisoEdital(avisoEdital)
        );
        return this.avisoEditalRepository.save(avisoEdital);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public AvisoEdital save(@PathVariable final long id, @RequestBody final AvisoEdital avisoEdital) {
        avisoEdital.setId(id);
        avisoEdital.getCategoriasAvisosEditais().forEach(categoriaAvisoEdital ->
                categoriaAvisoEdital.setAvisoEdital(avisoEdital)
        );
        return this.avisoEditalRepository.save(avisoEdital);
    }

    @PostMapping("/{id}/anexos")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo saveArquivoExterno(@PathVariable("id") final long publicacaoId, @RequestBody final Anexo arquivo) {
        arquivo.setPublicacao(new AvisoEdital(publicacaoId));
        return anexoRepository.save(arquivo);
    }

    @PutMapping("/{id}/anexos/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo updateArquivoExterno(@PathVariable("id") final long publicacaoId,
                                      @PathVariable("anexoId") final long anexoId,
                                      @RequestBody final Anexo anexo) {
        anexo.setId(anexoId);
        anexo.setPublicacao(new AvisoEdital(publicacaoId));
        return anexoRepository.save(anexo);
    }

    @PostMapping("/{id}/anexos/{nome}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean saveArquivoWithBytes(@PathVariable("id") final long publicacaoId,
                                        @PathVariable final String nome, final HttpServletRequest request) {

        final Anexo anexo = new Anexo();
        anexo.setPublicacao(new AvisoEdital(publicacaoId));

        populeArquivo(anexo, nome, request);

        anexoRepository.save(anexo);

        return true;
    }

    @PostMapping("/{id}/anexos/{nome}/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_EDITAL_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean updateArquivoWithBytes(@PathVariable("id") final long publicacaoId, @PathVariable final String nome,
                                          @PathVariable("anexoId") final long anexoId, final HttpServletRequest request) {

        final Anexo anexo = this.anexoRepository.findById(anexoId).orElseGet(Anexo::new);
        anexo.setPublicacao(new AvisoEdital(publicacaoId));

        populeArquivo(anexo, nome, request);

        anexoRepository.save(anexo);

        return true;
    }

    @Transactional
    @GetMapping(value = "{id}/anexos/{nome}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getAnexos(@PathVariable("id") final long publicacaoId, @PathVariable final String nome) {
        return super.getAnexos(publicacaoId, nome);
    }

    @Transactional
    @DeleteMapping(value = "{id}/anexos/{anexoId}")
    public Boolean deleteAnexo(@PathVariable("id") final long publicacaoId, @PathVariable final long anexoId) {
        super.deleteAnexo(publicacaoId, anexoId);
        return true;
    }

    @Transactional
    @GetMapping(value = "{id}/anexos")
    public Page<Anexo> getAnexos(@PathVariable("id") final long publicacaoId) {
        return super.getAnexos(publicacaoId);
    }
}
