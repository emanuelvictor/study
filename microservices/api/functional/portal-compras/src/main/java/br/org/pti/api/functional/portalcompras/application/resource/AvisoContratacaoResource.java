package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.IAnexoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IAvisoContratacaoRepository;
import br.org.pti.api.functional.portalcompras.application.resource.generic.AbstractArquivoResource;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Anexo;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.AvisoContratacao;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Modalidade;
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

@RestController
@RequestMapping({Roles.AVISO_CONTRATACAO_MAPPING_RESOURCE, "/sistema/" + Roles.AVISO_CONTRATACAO_MAPPING_RESOURCE})
public class AvisoContratacaoResource extends AbstractArquivoResource {

    /**
     *
     */
    private final IAvisoContratacaoRepository avisoContratacaoRepository;

    /**
     * @param anexoRepository            {IAnexoRepository}
     * @param avisoContratacaoRepository {IAvisoContratacaoRepository}
     */
    @Autowired
    public AvisoContratacaoResource(final IAnexoRepository anexoRepository, final IAvisoContratacaoRepository avisoContratacaoRepository) {
        super(anexoRepository, null);
        this.avisoContratacaoRepository = avisoContratacaoRepository;
    }

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('" + AVISO_CONTRATACAO_GET_ROLE + "' , '" + ADMINISTRADOR + "')")
    public Page<AvisoContratacao> listByFilters(final String defaultFilter, final Modalidade modalidade,
                                                final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio, final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim, final Pageable pageable) {

        LocalDate min = null;
        LocalDate max = null;

        if (dataInicio != null) {
            min = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (dataFim != null) {
            max = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        return this.avisoContratacaoRepository.listByFilters(defaultFilter, modalidade, min, max, pageable);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('" + AVISO_CONTRATACAO_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Optional<AvisoContratacao> findById(@PathVariable final long id) {
        return this.avisoContratacaoRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public AvisoContratacao save(@RequestBody final AvisoContratacao avisoContratacao) {
        return this.avisoContratacaoRepository.save(avisoContratacao);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public AvisoContratacao save(@PathVariable final long id, @RequestBody final AvisoContratacao avisoContratacao) {
        avisoContratacao.setId(id);
        return this.avisoContratacaoRepository.save(avisoContratacao);
    }

    @PostMapping("/{id}/anexos")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo saveArquivoExterno(@PathVariable("id") final long publicacaoId, @RequestBody final Anexo arquivo) {
        arquivo.setPublicacao(new AvisoContratacao(publicacaoId));
        return anexoRepository.save(arquivo);
    }

    @PutMapping("/{id}/anexos/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo updateArquivoExterno(@PathVariable("id") final long publicacaoId,
                                      @PathVariable("anexoId") final long anexoId,
                                      @RequestBody final Anexo anexo) {
        anexo.setId(anexoId);
        anexo.setPublicacao(new AvisoContratacao(publicacaoId));
        return anexoRepository.save(anexo);
    }

    @PostMapping("/{id}/anexos/{nome}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean saveArquivoWithBytes(@PathVariable("id") final long publicacaoId,
                                        @PathVariable final String nome, final HttpServletRequest request) {

        final Anexo anexo = new Anexo();
        anexo.setPublicacao(new AvisoContratacao(publicacaoId));

        populeArquivo(anexo, nome, request);

        anexoRepository.save(anexo);

        return true;
    }

    @PostMapping("/{id}/anexos/{nome}/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.AVISO_CONTRATACAO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean updateArquivoWithBytes(@PathVariable("id") final long publicacaoId, @PathVariable final String nome,
                                          @PathVariable("anexoId") final long anexoId, final HttpServletRequest request) {

        final Anexo anexo = this.anexoRepository.findById(anexoId).orElseGet(Anexo::new);
        anexo.setPublicacao(new AvisoContratacao(publicacaoId));

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