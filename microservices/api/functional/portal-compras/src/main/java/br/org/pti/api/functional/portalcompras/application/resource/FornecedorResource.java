package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.application.context.HTTPContextHolder;
import br.org.pti.api.functional.portalcompras.domain.repository.IDocumentoRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.IFornecedorRepository;
import br.org.pti.api.functional.portalcompras.domain.repository.dtos.FornecedorWsExterno;
import br.org.pti.api.functional.portalcompras.domain.repository.dtos.email.AnexoEmail;
import br.org.pti.api.functional.portalcompras.domain.repository.dtos.email.Email;
import br.org.pti.api.functional.portalcompras.domain.service.FornecedorService;
import br.org.pti.api.functional.portalcompras.infrastructure.aid.Utils;
import br.org.pti.api.functional.portalcompras.application.resource.generic.AbstractArquivoResource;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.TipoCadastroTipoDocumento;
import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.Documento;
import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.Fornecedor;
import br.org.pti.api.functional.portalcompras.domain.entity.fornecedor.StatusFornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping({Roles.FORNECEDOR_MAPPING_RESOURCE, "/sistema/" + Roles.FORNECEDOR_MAPPING_RESOURCE})
public class FornecedorResource extends AbstractArquivoResource {

    /**
     *
     */
    private final FornecedorService fornecedorService;

    /**
     *
     */
    private final IFornecedorRepository fornecedorRepository;

    /**
     * @param fornecedorService   {FornecedorService}
     * @param documentoRepository {IDocumentoRepository}
     */
    @Autowired
    public FornecedorResource(final FornecedorService fornecedorService, final IDocumentoRepository documentoRepository,
                              final IFornecedorRepository fornecedorRepository) {
        super(null, documentoRepository);
        this.fornecedorService = fornecedorService;
        this.fornecedorRepository = fornecedorRepository;
    }

    private static void populeDocumento(final Documento documento, final long fornecedorId, final Boolean aprovado, final long tipoCadastroTipoDocumentoId) {
        documento.setTipoCadastroTipoDocumento(new TipoCadastroTipoDocumento(tipoCadastroTipoDocumentoId));
        documento.setFornecedor(new Fornecedor(fornecedorId));
        documento.setAprovado(aprovado);
    }

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<Fornecedor>
     */
    @GetMapping("exists")
    public Page<Fornecedor> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.fornecedorService.listByFilters(defaultFilter, null, null, null, null, null, null, pageable);
    }

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<Fornecedor>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Page<Fornecedor> listByFilters(final String defaultFilter,
                                          final StatusFornecedor status,
                                          final Boolean vencidoFilter,
                                          final String[] atividadesEconomicasFilter,
                                          final Long[] categoriasFilter,
                                          final Long[] estadosFilter,
                                          final Long[] cidadesFilter,
                                          final Pageable pageable) {

        return this.fornecedorService.listByFilters(
                defaultFilter, status, vencidoFilter,
                Utils.getListFromArray(atividadesEconomicasFilter), Utils.getListFromArray(categoriasFilter),
                Utils.getListFromArray(estadosFilter), Utils.getListFromArray(cidadesFilter),
                pageable
        );
    }

    /**
     * @param id long
     * @return Fornecedor
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor findById(@PathVariable final long id) {
        return this.fornecedorService.findById(id);
    }

    /**
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PostMapping("{recap}")
    public Fornecedor save(@RequestBody final Fornecedor fornecedor, @PathVariable("recap") final String recap) {
        return this.fornecedorService.save(fornecedor, recap);
    }

    /**
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor save(@RequestBody final Fornecedor fornecedor) {
        return this.fornecedorService.save(fornecedor);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor save(@PathVariable final long id, @RequestBody final Fornecedor fornecedor) {
        return this.fornecedorService.save(id, fornecedor);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PutMapping("/{id}/sendToApprove")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor sendToApprove(@PathVariable final long id, @RequestBody final Fornecedor fornecedor) {
        return this.fornecedorService.sendToApprove(id, fornecedor);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PutMapping("/{id}/aprovar")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_APPROVE_ACTIVATE_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor aprovar(@PathVariable final long id, @RequestBody final Fornecedor fornecedor) {
        return this.fornecedorService.aprovar(id, fornecedor);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @PutMapping("/{id}/recusar")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_APPROVE_ACTIVATE_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Fornecedor recusar(@PathVariable final long id, @RequestBody final Fornecedor fornecedor) {
        return this.fornecedorService.recusar(id, fornecedor);
    }

    /**
     * @return {String}
     */
    @GetMapping("sitekey")
    public String getSiteKey() {
        return this.fornecedorService.getSiteKey();
    }

    @PostMapping("/{id}/documentos/{tipoCadastroTipoDocumentoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Documento saveArquivoExterno(@PathVariable("id") final long fornecedorId,
                                        @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                        @RequestBody final Documento arquivo) {
        arquivo.setTipoCadastroTipoDocumento(new TipoCadastroTipoDocumento(tipoCadastroTipoDocumentoId));
        arquivo.setFornecedor(new Fornecedor(fornecedorId));
        return documentoRepository.save(arquivo);
    }

    @PutMapping("/{id}/documentos/{documentoId}/{tipoCadastroTipoDocumentoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Documento updateArquivoExterno(@PathVariable("id") final long fornecedorId,
                                          @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                          @PathVariable("documentoId") final long documentoId, @RequestBody final Documento documento) {
        documento.setTipoCadastroTipoDocumento(new TipoCadastroTipoDocumento(tipoCadastroTipoDocumentoId));
        documento.setId(documentoId);
        documento.setFornecedor(new Fornecedor(fornecedorId));
        return documentoRepository.save(documento);
    }

    @PostMapping("/{id}/documentos/{nome}/{tipoCadastroTipoDocumentoId}/aprovado/{aprovado}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean saveArquivoWithBytes(@PathVariable("id") final long fornecedorId, @PathVariable final String nome,
                                        @PathVariable(value = "aprovado", required = false) final Boolean aprovado,
                                        @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                        final HttpServletRequest request) {

        final Documento documento = new Documento();

        populeDocumento(documento, fornecedorId, aprovado, tipoCadastroTipoDocumentoId);

        populeArquivo(documento, nome, request);

        documentoRepository.save(documento);

        return true;
    }

    @PostMapping("/{id}/documentos/{nome}/{documentoId}/{tipoCadastroTipoDocumentoId}/aprovado/{aprovado}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean updateArquivoWithBytes(@PathVariable("id") final long fornecedorId, @PathVariable final String nome,
                                          @PathVariable(value = "aprovado", required = false) final Boolean aprovado,
                                          @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                          @PathVariable("documentoId") final long documentoId, final HttpServletRequest request) {

        final Documento documento = this.documentoRepository.findById(documentoId).orElseGet(Documento::new);

        populeDocumento(documento, fornecedorId, aprovado, tipoCadastroTipoDocumentoId);

        populeArquivo(documento, nome, request);

        documentoRepository.save(documento);

        return true;
    }

    @PostMapping("/{id}/documentos/{nome}/{tipoCadastroTipoDocumentoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean saveArquivoWithBytes(@PathVariable("id") final long fornecedorId, @PathVariable final String nome,
                                        @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                        final HttpServletRequest request) {

        final Documento documento = new Documento();

        populeDocumento(documento, fornecedorId, null, tipoCadastroTipoDocumentoId);

        populeArquivo(documento, nome, request);

        documentoRepository.save(documento);

        return true;
    }

    @PostMapping("/{id}/documentos/{nome}/{documentoId}/{tipoCadastroTipoDocumentoId}")
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_PUT_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    public Boolean updateArquivoWithBytes(@PathVariable("id") final long fornecedorId, @PathVariable final String nome,
                                          @PathVariable("tipoCadastroTipoDocumentoId") final long tipoCadastroTipoDocumentoId,
                                          @PathVariable("documentoId") final long documentoId, final HttpServletRequest request) {

        final Documento documento = this.documentoRepository.findById(documentoId).orElseGet(Documento::new);

        populeDocumento(documento, fornecedorId, null, tipoCadastroTipoDocumentoId);

        populeArquivo(documento, nome, request);

        documentoRepository.save(documento);

        return true;
    }

    @Transactional
    @GetMapping(value = "{id}/documentos/{nome}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDocumentos(@PathVariable("id") final long fornecedorId, @PathVariable final String nome) {
        return super.getDocumentos(fornecedorId, nome);
    }

    @Transactional
    @DeleteMapping(value = "{id}/documentos/{documentoId}")
    public Boolean deleteDelete(@PathVariable("id") final long fornecedorId, @PathVariable final long documentoId) {
        super.deleteDocumento(fornecedorId, documentoId);
        return true;
    }

    @Transactional
    @GetMapping(value = "{id}/documentos")
    public Page<Documento> getDocumentos(@PathVariable("id") final long fornecedorId) {
        return super.getDocumentos(fornecedorId);
    }

    @ResponseBody
    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "{id}/crc", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] exportCRC(@PathVariable final long id) {
        return fornecedorService.exportCRC(id);
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "emCriacao", method = RequestMethod.GET)
    public int getCountFornecedoresEmCriacao() {
        return fornecedorRepository.getCountFornecedoresEmCriacao();
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "emAnalise", method = RequestMethod.GET)
    public int getCountFornecedoresEmAnalise() {
        return fornecedorRepository.getCountFornecedoresEmAnalise();
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "aprovados", method = RequestMethod.GET)
    public int getCountFornecedoresAprovados() {
        return fornecedorRepository.getCountFornecedoresAprovados();
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "recusados", method = RequestMethod.GET)
    public int getCountFornecedoresRecusados() {
        return fornecedorRepository.getCountFornecedoresRecusados();
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_GET_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @RequestMapping(value = "vencidos", method = RequestMethod.GET)
    public int getCountFornecedoresVencidos() {
        return fornecedorRepository.getCountFornecedoresVencidos(LocalDateTime.now().plusYears(-1));
    }

    @PreAuthorize("hasAnyAuthority('" + Roles.FORNECEDOR_POST_ROLE + "' , '" + Roles.ADMINISTRADOR + "')")
    @PostMapping("sendMassMail/")
    public boolean sendMassMail(@RequestParam("assunto") final String assunto,
                                @RequestParam(value = "status", required = false) final StatusFornecedor status,
                                @RequestParam("conteudo") final String conteudo,
                                @RequestParam(value = "categorias", required = false) final Long[] categorias,
                                @RequestParam(value = "countFiles", required = false) final Integer countFiles
            , final HttpServletRequest request) throws IOException {


        final Email email = new Email();
        email.setAssunto(assunto);
        email.setStatus(status);
        email.setConteudo(conteudo);
        email.setFornecedores(fornecedorService.listByFilters(null, status, null, null, Utils.getListFromArray(categorias), null, null, null).getContent());
        email.setUrl(HTTPContextHolder.getServerURL());

        if (countFiles != null) {

            final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            for (int i = 0; i < countFiles; i++) {
                final AnexoEmail anexoEmail = new AnexoEmail();
                final MultipartFile multipartFile = multipartRequest.getFile("file" + i);
                anexoEmail.setType(Objects.requireNonNull(multipartRequest.getFile("file" + i)).getContentType());
                anexoEmail.setNome(Objects.requireNonNull(multipartRequest.getFile("file" + i)).getName());
                anexoEmail.setConteudo(Objects.requireNonNull(multipartFile).getBytes());
                email.getAnexosEmail().add(anexoEmail);
            }
        }

        return fornecedorService.sendMassMail(email);
    }

    /**
     * @param cnpj String
     * @return FornecedorWsExterno
     */
    @GetMapping(value = "cnpj/{cnpj}")
    public FornecedorWsExterno findFornecedorWsExternoByCNPJ(@PathVariable final String cnpj) {
        return fornecedorService.findFornecedorWsExternoByCNPJ(cnpj);
    }

}
