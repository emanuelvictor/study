package br.org.pti.compras.domain.service;

import br.com.caelum.stella.tinytype.CNPJ;
import br.org.pti.compras.application.context.ContextHolder;
import br.org.pti.compras.application.context.HTTPContextHolder;
import br.org.pti.compras.application.i18n.MessageSourceHolder;
import br.org.pti.compras.domain.entity.cadastros.Banco;
import br.org.pti.compras.domain.entity.configuracao.Pessoa;
import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.entity.fornecedor.*;
import br.org.pti.compras.domain.repository.*;
import br.org.pti.compras.domain.repository.dtos.FornecedorProtheus;
import br.org.pti.compras.domain.repository.dtos.FornecedorWsExterno;
import br.org.pti.compras.domain.repository.dtos.email.Email;
import br.org.pti.compras.domain.repository.mail.IFornecedorAbstractMailRepository;
import br.org.pti.compras.domain.repository.mail.IUsuarioAbstractMailRepository;
import br.org.pti.compras.domain.repository.reports.ICRCReportRepository;
import br.org.pti.compras.domain.repository.rest.ICNPJRestRepository;
import br.org.pti.compras.domain.repository.rest.ICidadesFeignRepository;
import br.org.pti.compras.domain.repository.rest.IFornecedoresFeignRepository;
import br.org.pti.compras.infrastructure.support.SSLSupport;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.org.pti.compras.infrastructure.aid.Utils.normalizeSymbolsAndAccents;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorService.class);
    /**
     *
     */
    private final OAuth2RestOperations restTemplate;
    /**
     *
     */
    private final UsuarioService usuarioService;
    /**
     *
     */
    private final PasswordEncoder passwordEncoder;
    /**
     *
     */
    private final IBancoRepository bancoRepository;
    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;
    /**
     *
     */
    private final ICNPJRestRepository icnpjRestRepository;
    /**
     *
     */
    private final ICRCReportRepository crcReportRepository;
    /**
     *
     */
    private final IDocumentoRepository documentoRepository;
    /**
     *
     */
    private final IFornecedorRepository fornecedorRepository;
    /**
     *
     */
    private final ICidadesFeignRepository cidadesFeignRepository;
    /**
     *
     */
    private final IUsuarioAbstractMailRepository usuarioMailRepository;
    /**
     *
     */
    private final IDadosRecebimentoRepository dadosRecebimentoRepository;
    /**
     *
     */
    private final IFornecedoresFeignRepository fornecedoresFeignRepository;
    /**
     *
     */
    private final IFornecedorAbstractMailRepository fornecedorMailRepository;
    /**
     *
     */
    private final ICategoriaFornecedorRepository categoriaFornecedorRepository;
    /**
     *
     */
    @Value("${oauth.endpoints.fornecedores}")
    private String fornecedoresUrl;
    /**
     *
     */
    @Value("${info.url}")
    private String url;
    /**
     *
     */
    @Value("${google.recaptcha.site-key}")
    private String siteKey;
    /**
     *
     */
    @Value("${google.recaptcha.secret-key}")
    private String secretKey;
    /**
     *
     */
    @Value("${google.recaptcha.urltoverify}")
    private String urlToVerify;

    /**
     * @param documento String
     * @return String
     */
    private static String extractLoja(final String documento) {
        return new CNPJ(documento).getNumeroFormatado().substring(new CNPJ(documento).getNumeroFormatado().indexOf("/") + 1, new CNPJ(documento).getNumeroFormatado().indexOf("-"));
    }

    /**
     * @param id {long}
     */
    private static void itsMe(final long id) {
        Assert.isTrue(ContextHolder.isAdm(id) || ContextHolder.itsMe(id), "Acesso negado!");
    }

    /**
     * @param filter   {String}
     * @param status   status
     * @param pageable Pageable
     * @return Page<Fornecedor>
     */
    @Transactional(readOnly = true)
    public Page<Fornecedor> listByFilters(final String filter, final StatusFornecedor status, final Boolean vencidosFilter,
                                          final List<String> atividadesEconomicasFilter, final List<Long> categoriasFilter,
                                          final List<Long> estadosFilter, final List<Long> cidadesFilter, final Pageable pageable) {
        return this.fornecedorRepository.listByFilters(filter, status, (vencidosFilter != null && vencidosFilter) || (status != null && status.equals(StatusFornecedor.APROVADO)) ? LocalDateTime.now().plusYears(-1) : null, atividadesEconomicasFilter, categoriasFilter, estadosFilter, cidadesFilter, pageable);
    }

    /**
     * @param id long
     * @return Fornecedor
     */
    @Transactional(readOnly = true)
    public Fornecedor findById(final long id) {
        return this.fornecedorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));
    }

    /**
     * Inserção do fornecedor a partir do portal.
     *
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor save(final Fornecedor fornecedor, final String recap) {

        Assert.isTrue(this.usuarioService.findByLdapUsername(fornecedor.getUsuario().getUsername()) == null, "Esse e-mail é interno!");

        Assert.isTrue(recap != null && this.checkRecaptcha(recap), "Você é um robo?");

        fornecedor.getUsuario().setCodigo(UsuarioService.gerarCodigo());

        fornecedor.getUsuario().setFornecedor(fornecedor);

        fornecedor.setStatus(StatusFornecedor.EM_CRIACAO);

        this.save(fornecedor);

        this.usuarioMailRepository.sendNewUserAccount(fornecedor.getUsuario(), HTTPContextHolder.getServerURL() + "/sistema/#/cadastrar-senha/" + fornecedor.getUsuario().getCodigo());

        return fornecedor;
    }

    /**
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor save(final Fornecedor fornecedor) {

        if (fornecedor.getEmails().size() > 0 /*&& Objects.equals(fornecedor.getEmails().stream().findFirst().orElse(null), fornecedor.getUsuario().getEmail())*/)
            fornecedor.getEmails().remove(fornecedor.getUsuario().getEmail());

        if (fornecedor.getUsuario().getSenha() == null && fornecedor.getUsuario().getId() == null)
            fornecedor.getUsuario().setSenha(passwordEncoder.encode(Usuario.DEFAULT_PASSWORD));
        else if (fornecedor.getUsuario().getId() != null)
            fornecedor.getUsuario().setSenha(this.usuarioRepository.findById(fornecedor.getUsuario().getId()).orElse(new Usuario()).getSenha());

        fornecedor.getUsuario().validateDocumento();

        // Handler de categorias durante a inserção
        fornecedor.getCategoriasFornecedor().forEach(categoriaFornecedor -> {
            if (fornecedor.getId() != null) {
                final CategoriaFornecedor categoriaFornecedorExistente = categoriaFornecedorRepository.findByFornecedorIdAndCategoriaId(fornecedor.getId(), categoriaFornecedor.getCategoria().getId());
                if (categoriaFornecedorExistente != null)
                    categoriaFornecedor.setId(categoriaFornecedorExistente.getId());
            }
            categoriaFornecedor.setFornecedor(fornecedor);
        });

        // Handler de Dados de Recebimento
        if (fornecedor.getDadosRecebimento().getContaBancaria().getBanco() != null) {

            final Banco bancoDB = bancoRepository.findBancoByCodigo(fornecedor.getDadosRecebimento().getContaBancaria().getBanco().getCodigo());
            if (bancoDB == null)
                fornecedor.getDadosRecebimento().getContaBancaria().setBanco(bancoRepository.save(fornecedor.getDadosRecebimento().getContaBancaria().getBanco()));
            else {
                fornecedor.getDadosRecebimento().getContaBancaria().getBanco().setId(bancoDB.getId());
                fornecedor.getDadosRecebimento().getContaBancaria().setBanco(bancoRepository.save(fornecedor.getDadosRecebimento().getContaBancaria().getBanco()));
            }


            final DadosRecebimento dadosRecebimento = dadosRecebimentoRepository.findDadosRecebimentoByAgenciaAndBancoIdAndNumeroAndDigito(
                    fornecedor.getDadosRecebimento().getContaBancaria().getAgencia(),
                    fornecedor.getDadosRecebimento().getContaBancaria().getBanco().getId(),
                    fornecedor.getDadosRecebimento().getContaBancaria().getNumero(),
                    fornecedor.getDadosRecebimento().getContaBancaria().getDigito());
            if (dadosRecebimento != null) {
                fornecedor.getDadosRecebimento().setId(dadosRecebimento.getId());
                fornecedor.getDadosRecebimento().getContaBancaria().setId(dadosRecebimento.getContaBancaria().getId());
            }
        }

        // Trata cidade
        if (fornecedor.getEndereco() != null && fornecedor.getEndereco().getCidade() != null && fornecedor.getEndereco().getCidade().getId() == null)
            fornecedor.getEndereco().setCidade(null);

        return fornecedorRepository.save(fornecedor);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor sendToApprove(long id, Fornecedor fornecedor) {

        fornecedor.setId(id);

        fornecedor.setStatus(StatusFornecedor.EM_ANALISE);

        return this.save(fornecedor);
    }

    /**
     *
     */
    public void sendToVencidos() {

        LOGGER.info("Verificando fornecedores vencidos");

        final List<Fornecedor> fornecedoresVencidos = this.fornecedorRepository.listFornecedoresAVencer(LocalDateTime.now().plusYears(-1), LocalDateTime.now().plusYears(-1).plusMonths(1));

        if (!fornecedoresVencidos.isEmpty()) {

            LOGGER.info(fornecedoresVencidos.size() + " fornecedores vencidos encontrados!");
            LOGGER.info("Enviando e-mail para os seguintes fornecedores" + fornecedoresVencidos.stream().map(fornecedor -> fornecedor.getUsuario().getEmail()).collect(Collectors.joining(",")));

            fornecedoresVencidos.forEach(fornecedor -> {

                this.fornecedorMailRepository.sendMailToFornecedoresVencidos(fornecedor, this.url);

                this.fornecedorRepository.updateSentEmailVencido(fornecedor.getId());
            });
        }
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor aprovar(final long id, final Fornecedor fornecedor) {

        fornecedor.setId(id);

        Assert.isTrue(documentoRepository.findAllByFornecedorId(id, null).getContent().stream().noneMatch(documento -> documento.getAprovado() != null && documento.getAprovado().equals(false)), "Aprove todos os documentos primeiro");

        fornecedor.setStatus(StatusFornecedor.APROVADO);

        this.save(fornecedor);

        if (fornecedor.getSouEmpresa()) {
            fornecedor.setCodigoProtheus(this.aprovarNoProtheus(fornecedor)); // TODO protheus de testes ainda não está ok

            this.save(fornecedor);

            System.out.println(fornecedor.getCodigoProtheus());
        }

        this.fornecedorMailRepository.sendFornecedorAprovado(fornecedor, HTTPContextHolder.getServerURL());

        return fornecedor;
    }

    /**
     * @param fornecedor {Fornecedor}
     * @return {Long}
     */
    private Long aprovarNoProtheus(final Fornecedor fornecedor) {

        final FornecedorProtheus f = new FornecedorProtheus();
        f.setNome(fornecedor.getRazaoSocial());
        f.setNomeFantasia(fornecedor.getUsuario().getNome());
        f.setDocumento(fornecedor.getUsuario().getDocumento());
        f.setLoja(extractLoja(fornecedor.getUsuario().getDocumento()));
        f.setInscricaoEstadual(fornecedor.getInscricaoEstadual());
        f.setInscricaoMunicipal(fornecedor.getInscricaoMunicipal());
        f.setTipo(fornecedor.getSouEmpresa() ? "J" : "F");
        f.setTipoPessoa(fornecedor.getAtividadesEconomicas().contains(TipoAtividadeEconomica.INDUSTRIA_COMERCIO) ? "CI" : "OS");
        f.setTipoPessoaJuridica(fornecedor.getTipoPessoaJuridica().equals(TipoPessoaJuridica.MICROEMPRESA) ? "1" : (fornecedor.getTipoPessoaJuridica().equals(TipoPessoaJuridica.PEQUENO_PORTE) ? "2" : (fornecedor.getTipoPessoaJuridica().equals(TipoPessoaJuridica.NAO_OPTANTE) ? "5" : (fornecedor.getCooperativa() ? "4" : "3"))));
        f.setEmail(fornecedor.getUsuario().getEmail());
        f.setMunicipio(this.listByFilters(fornecedor));
        f.setPais("1058");
        f.setUf(fornecedor.getEndereco().getCidade().getEstado().getUf());
        f.setRua(fornecedor.getEndereco().getLogradouro());
        f.setNumero(fornecedor.getEndereco().getNumero());
        f.setBairro(fornecedor.getEndereco().getBairro() != null && fornecedor.getEndereco().getBairro().length() > 0 ? fornecedor.getEndereco().getBairro() : "SEM BAIRRO");
        f.setComplemento(fornecedor.getEndereco().getComplemento());
        f.setNatureza("0103002");
        f.setCep(fornecedor.getEndereco().getCep().replace("-", ""));

        final String telefone = Objects.requireNonNull(fornecedor.getTelefones().stream().findFirst().orElse(null)).replaceAll("\\s+", "").replace("(", "").replace(")", "").replace("-", "");
        f.setTelefone(telefone.substring(2));
        f.setDdd(telefone.substring(0, 2));

        f.setBanco(fornecedor.getDadosRecebimento().getContaBancaria().getBanco().getCodigo());
        f.setAgencia(fornecedor.getDadosRecebimento().getContaBancaria().getAgencia());
        f.setNumeroConta(fornecedor.getDadosRecebimento().getContaBancaria().getNumero());
        f.setTipoConta(fornecedor.getDadosRecebimento().getContaBancaria().getTipoContaBancaria().equals(TipoContaBancaria.CONTA_CORRENTE) ? "C" : "P");
        f.setFormaPagamento(fornecedor.getDadosRecebimento().getFormaPagamento().equals(FormaPagamento.DEPOSITO_BANCARIO) ? "D" : "B");
        f.setSimplesNacional(fornecedor.getSimplesNacional() ? "S" : "N");
        f.setDigitoConta(fornecedor.getDadosRecebimento().getContaBancaria().getDigito());
        f.setRecolhimentoPIS("S");
        f.setRecolhimentoCOFINS("S");
        f.setRecolhimentoCSLL("S");
        f.setRecolhimentoISS("N");
        f.setCalculaIRRF("1");
        f.setCalculaINSS("N");
        f.setCooperativa(fornecedor.getCooperativa() ? "S" : "N");
        f.setCodigo(fornecedor.getCodigoProtheus() != null ? fornecedor.getCodigoProtheus().toString() : null);

        System.out.println();
        System.out.println(this.restTemplate.getAccessToken().getValue());
        System.out.println();

        try {
            final Map<Object, Object> fornecedorProtheus = fornecedoresFeignRepository.findByDocumento(f.getDocumento()).getBody();
            if (fornecedorProtheus != null) {
                f.setCodigo((String) fornecedorProtheus.get("codigo"));
                f.setLoja((String) fornecedorProtheus.get("loja"));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(new Gson().toJson(f));
        System.out.println();

        if (f.getCodigo() == null)
            return Long.parseLong(this.fornecedoresFeignRepository.save(f).trim());
        else {
            this.fornecedoresFeignRepository.update(f);
            return fornecedor.getCodigoProtheus();
        }
    }

    /**
     * @param fornecedor Fornecedor
     * @return String
     */
    public String listByFilters(final Fornecedor fornecedor) {
        return Objects.requireNonNull(cidadesFeignRepository.listByFilters(fornecedor.getEndereco().getCidade().getEstado().getUf())).stream()
                .filter(cidade ->
                        cidade.getNome().equals(normalizeSymbolsAndAccents(fornecedor.getEndereco().getCidade().getNome()).toUpperCase())
                )
                .map(br.org.pti.compras.domain.repository.dtos.Cidade::getCodigo)
                .findFirst().orElse(null);
    }

    /**
     * @param id         long
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor recusar(final long id, final Fornecedor fornecedor) {

        fornecedor.setId(id);

        fornecedor.setStatus(StatusFornecedor.RECUSADO);

        this.save(fornecedor);

        this.fornecedorMailRepository.sendFornecedorRecusado(fornecedor, HTTPContextHolder.getServerURL());

        return fornecedor;
    }

    /**
     * @param fornecedor Fornecedor
     * @return Fornecedor
     */
    @Transactional
    public Fornecedor save(final long id, final Fornecedor fornecedor) {

        fornecedor.setId(id);

        final Usuario usuario = this.usuarioService.findById(fornecedor.getUsuario().getId());

        itsMe(usuario.getId());

        fornecedor.getUsuario().setSenha(usuario.getPassword());

        return this.save(fornecedor);
    }

    /**
     * @return String
     */
    public String getSiteKey() {
        return this.siteKey;
    }

    /**
     * @param cnpj String
     * @return FornecedorWsExterno
     */
    public FornecedorWsExterno findFornecedorWsExternoByCNPJ(final String cnpj) {
        return icnpjRestRepository.findFornecedorWsExternoByCNPJ(cnpj);
    }

    /**
     * @param recap String
     * @return boolean
     */
    private boolean checkRecaptcha(final String recap) {

        try {
            final String urlGoogle = this.urlToVerify + "?secret=%s&response=%s";
            final String urlFormatada = String.format(urlGoogle, this.secretKey, recap);
            // TODO substituir pelo feign
            final HttpURLConnection conn = (HttpURLConnection) new URL(urlFormatada).openConnection();
            conn.setRequestMethod("GET");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // Disable TLS 1.0
            if (conn instanceof HttpsURLConnection) {
                ((HttpsURLConnection) conn).setSSLSocketFactory(new SSLSupport());
            }

            final StringBuilder outputString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                outputString.append(line);
            }
            final FornecedorService.CaptchaResponse capRes = new Gson().fromJson(outputString.toString(), FornecedorService.CaptchaResponse.class);
            return capRes.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param id long
     * @return byte[]
     */
    public byte[] exportCRC(final long id) {
        final Fornecedor fornecedor = this.findById(id);
        return crcReportRepository.exportCRC(id, Pessoa.formatDocumento(fornecedor.getUsuario().getDocumento()), fornecedor.getSouEmpresa() ? "CNPJ:" : "CPF:").toByteArray();
    }

    /**
     * @param email {Email}
     * @return {boolean}
     */
    public boolean sendMassMail(final Email email) {
        if (!email.getFornecedores().isEmpty()) {
            fornecedorMailRepository.sendMassMail(email);
            return true;
        }
        throw new RuntimeException("Não há nenhum fornecedor vinculado a essas categorias, e/ou com esse Status");
    }

    /**
     * Classe auxiliar para o retorno do reCaptcha
     */
    private class CaptchaResponse {

        private boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

    }

}
