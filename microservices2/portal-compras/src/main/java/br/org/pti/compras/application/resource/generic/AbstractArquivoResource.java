package br.org.pti.compras.application.resource.generic;

import br.org.pti.compras.domain.entity.fornecedor.Documento;
import br.org.pti.compras.domain.entity.publicacoes.Anexo;
import br.org.pti.compras.domain.entity.publicacoes.generic.Arquivavel;
import br.org.pti.compras.domain.repository.IAnexoRepository;
import br.org.pti.compras.domain.repository.IDocumentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class AbstractArquivoResource {

    /**
     *
     */
    protected final IAnexoRepository anexoRepository;

    /**
     *
     */
    protected final IDocumentoRepository documentoRepository;

    /**
     * @param anexoRepository {IAnexoRepository}
     */

    public AbstractArquivoResource(final IAnexoRepository anexoRepository, final IDocumentoRepository documentoRepository) {
        this.anexoRepository = anexoRepository;
        this.documentoRepository = documentoRepository;
    }

    /**
     * Prepara arquivo para armazenamento.
     *
     * @param arquivavel {Arquivavel}
     * @param nome       {String}
     * @param request    {HttpServletRequest}
     */
    protected static void populeArquivo(final Arquivavel arquivavel, final String nome, final HttpServletRequest request) {

        try {

            final MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            final MultipartFile multipartFile = multipartRequest.getFile("file");

            arquivavel.setNome(nome);
            arquivavel.setExterno(false);

            Assert.notNull(multipartFile, "Arquivo sem conteúdo");
            Assert.notNull(multipartFile.getBytes(), "Arquivo sem conteúdo");
            Assert.notNull(multipartRequest.getFile("file"), "Arquivo inválido");

            arquivavel.setType(Objects.requireNonNull(multipartRequest.getFile("file")).getContentType());
            arquivavel.setConteudo(multipartFile.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * @param publicacaoId {long}
     * @param nome         {String}
     * @return {ResponseEntity<byte[]>}
     */
    @Transactional
    public ResponseEntity<byte[]> getAnexos(final long publicacaoId, final String nome) {
        final Anexo anexo = anexoRepository.findByPublicacaoIdAndNome(publicacaoId, nome);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(anexo.getType()))
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(anexo.getConteudo());
    }

    /**
     * @param publicacaoId {long}
     * @param anexoId      {long}
     * @return Boolean
     */
    @Transactional
    public Boolean deleteAnexo(final long publicacaoId, @PathVariable final long anexoId) {
        Assert.isTrue(publicacaoId == Objects.requireNonNull(this.anexoRepository.findById(anexoId).orElse(null)).getPublicacao().getId(), "Você não tem acesso á esse anexo");
        this.anexoRepository.deleteById(anexoId);
        return true;
    }

    /**
     * @param publicacaoId {long}
     * @return {Page<Anexo>}
     */
    @Transactional
    public Page<Anexo> getAnexos(final long publicacaoId) {
        return this.anexoRepository.findAllByPublicacaoId(publicacaoId, null);
    }

    /**
     * @param fornecedorId {long}
     * @param nome         {String}
     * @return {ResponseEntity<byte[]>}
     */
    @Transactional
    public ResponseEntity<byte[]> getDocumentos(final long fornecedorId, final String nome) {
        final Documento documento = documentoRepository.findByFornecedorIdAndNome(fornecedorId, nome);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(documento.getType()))
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(documento.getConteudo());
    }

    /**
     * @param fornecedorId {long}
     * @param anexoId      {long}
     * @return Boolean
     */
    @Transactional
    public Boolean deleteDocumento(final long fornecedorId, @PathVariable final long anexoId) {
        Assert.isTrue(fornecedorId == Objects.requireNonNull(documentoRepository.findById(anexoId).orElse(null)).getFornecedor().getId(), "Você não tem acesso á esse anexo");
        this.documentoRepository.deleteById(anexoId);
        return true;
    }

    /**
     * @param fornecedorId {long}
     * @return {Page<Anexo>}
     */
    @Transactional
    public Page<Documento> getDocumentos(final long fornecedorId) {
        return this.documentoRepository.findAllByFornecedorId(fornecedorId, null);
    }
}
