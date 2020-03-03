package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.IAnexoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IExtratoContratoRepository;
import br.org.pti.api.functional.portalcompras.application.resource.generic.AbstractArquivoResource;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Anexo;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.AvisoEdital;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.ExtratoContrato;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.InstrumentoJuridico;
import br.org.pti.api.functional.portalcompras.domain.repository.IAnexoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IExtratoContratoRepository;
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

@RestController
@RequestMapping({Roles.EXTRATO_CONTRATO_MAPPING_RESOURCE, "/sistema/" + Roles.EXTRATO_CONTRATO_MAPPING_RESOURCE})
public class ExtratoContratoResource extends AbstractArquivoResource {

    /**
     *
     */
    private final IExtratoContratoRepository extratoContratoRepository;

    /**
     * @param anexoRepository           {IAnexoRepository}
     * @param extratoContratoRepository {IExtratoContratoRepository}
     */
    @Autowired
    public ExtratoContratoResource(final IAnexoRepository anexoRepository, final IExtratoContratoRepository extratoContratoRepository) {
        super(anexoRepository, null);
        this.extratoContratoRepository = extratoContratoRepository;
    }

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('" + EXTRATO_CONTRATO_GET_ROLE + "' , '" + ADMINISTRADOR + "')")
    public Page<ExtratoContrato> listByFilters(final String defaultFilter, final InstrumentoJuridico instrumentoJuridico,
                                               final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio, final @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
                                               final Pageable pageable) {

        LocalDate min = null;
        LocalDate max = null;

        if (dataInicio != null) {
            min = dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if (dataFim != null) {
            max = dataFim.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        return this.extratoContratoRepository.listByFilters(defaultFilter, instrumentoJuridico, min, max, pageable);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('" + EXTRATO_CONTRATO_GET_ROLE + "', '" + ADMINISTRADOR + "')")
    public Optional<ExtratoContrato> findById(@PathVariable final long id) {
        return this.extratoContratoRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public ExtratoContrato save(@RequestBody final ExtratoContrato extratoContrato) {
        return this.extratoContratoRepository.save(extratoContrato);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public ExtratoContrato save(@PathVariable final long id, @RequestBody final ExtratoContrato extratoContrato) {
        extratoContrato.setId(id);
        return this.extratoContratoRepository.save(extratoContrato);
    }

    @PostMapping("/{id}/anexos")
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo saveArquivoExterno(@PathVariable("id") final long publicacaoId, @RequestBody final Anexo arquivo) {
        arquivo.setPublicacao(new AvisoEdital(publicacaoId));
        return anexoRepository.save(arquivo);
    }

    @PutMapping("/{id}/anexos/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Anexo updateArquivoExterno(@PathVariable("id") final long publicacaoId,
                                      @PathVariable("anexoId") final long anexoId,
                                      @RequestBody final Anexo anexo) {
        anexo.setId(anexoId);
        anexo.setPublicacao(new AvisoEdital(publicacaoId));
        return anexoRepository.save(anexo);
    }

    @PostMapping("/{id}/anexos/{nome}")
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean saveArquivoWithBytes(@PathVariable("id") final long publicacaoId,
                                        @PathVariable final String nome, final HttpServletRequest request) {

        final Anexo anexo = new Anexo();
        anexo.setPublicacao(new ExtratoContrato(publicacaoId));

        populeArquivo(anexo, nome, request);

        anexoRepository.save(anexo);

        return true;
    }

    @PostMapping("/{id}/anexos/{nome}/{anexoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.EXTRATO_CONTRATO_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean updateArquivoWithBytes(@PathVariable("id") final long publicacaoId, @PathVariable final String nome,
                                          @PathVariable("anexoId") final long anexoId, final HttpServletRequest request) {

        final Anexo anexo = this.anexoRepository.findById(anexoId).orElseGet(Anexo::new);
        anexo.setPublicacao(new ExtratoContrato(publicacaoId));

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
