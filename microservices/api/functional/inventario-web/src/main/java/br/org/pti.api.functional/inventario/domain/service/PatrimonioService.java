package br.org.pti.api.functional.inventario.domain.service;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.Localizacao;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.Patrimonio;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.dto.PatrimonioDTO;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.CentroCustoInventarioStatus;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.dto.CentroCustoDTO;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.dto.ColaboradorDTO;
import br.org.pti.api.functional.inventario.domain.repository.ICentroCustoInventarioRepository;
import br.org.pti.api.functional.inventario.domain.repository.ICentroCustoRepository;
import br.org.pti.api.functional.inventario.domain.repository.ILocalizacaoRepository;
import br.org.pti.api.functional.inventario.domain.repository.IPatrimonioRepository;
import br.org.pti.api.functional.inventario.domain.repository.feign.ICentroCustoFeignRepository;
import br.org.pti.api.functional.inventario.domain.repository.feign.ILocalizacaoFeignRepository;
import br.org.pti.api.functional.inventario.domain.repository.feign.IPatrimonioFeignRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class PatrimonioService {

    /**
     *
     */
    private final UsuarioService usuarioService;

    /**
     *
     */
    private final IPatrimonioRepository patrimonioRepository;

    /**
     *
     */
    private final ILocalizacaoRepository localizacaoRepository;

    /**
     *
     */
    private final ICentroCustoRepository centroCustoRepository;

    /**
     *
     */
    private final IPatrimonioFeignRepository patrimonioFeignRepository;

    /**
     *
     */
    private final ILocalizacaoFeignRepository localizacaoFeignRepository;

    /**
     *
     */
    private final ICentroCustoFeignRepository centrosCustoFeignRepository;

    /**
     *
     */
    private final ICentroCustoInventarioRepository centroCustoInventarioRepository;

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Patrimonio> listNovosPatrimoniosByCentroCustoCodigoAndFilters(final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        //
        return this.patrimonioRepository.listNovosPatrimoniosByCentroCustoCodigoAndFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, numeroPlaquetaFilter, pageable);
    }

    /**
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param centroCustoCodigoFilter
     * @param encontradoFilter
     * @param sobraFisicaFilter
     * @param transferidoFilter
     * @param pageable
     * @return
     */
    @Transactional
    public Page<Patrimonio> listAllPatrimoniosByCentroCustoCodigoAndFilters(final String descricaoPatrimonioFilter,
                                                                            final String descricaoLocalizacaoFilter,
                                                                            final String numeroPlaquetaFilter,
                                                                            final String centroCustoCodigoFilter,
                                                                            final Boolean encontradoFilter,
                                                                            final Boolean sobraFisicaFilter,
                                                                            final Boolean transferidoFilter,
                                                                            final Pageable pageable) {

        final Set<String> centrosCustosCodigos;
        if (!(ContextHolder.getAuthenticatedUser().isRoot() || ContextHolder.getAuthenticatedUser().getIsPatrimonio()))
            centrosCustosCodigos = ContextHolder.getAuthenticatedUser().getCentrosCusto().stream().map(CentroCusto::getCodigo).collect(Collectors.toSet());
        else
            centrosCustosCodigos = null;


//        final List<Patrimonio> patrimoniosNaBaseLocal = this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(
//                descricaoPatrimonioFilter,
//                descricaoLocalizacaoFilter,
//                numeroPlaquetaFilter,
//                centroCustoCodigoFilter,
//                null,
//                sobraFisicaFilter,
//                transferidoFilter,
//                centrosCustosCodigos == null ? null : (centrosCustosCodigos.isEmpty() ? null : centrosCustosCodigos),
//                new PageRequest(0, 1000000)).getContent();
//
//        final List<PatrimonioDTO> patrimoniosNoProtheus = this.patrimonioFeignRepository.listByFilters(centroCustoCodigoFilter, null, null, new PageRequest(0, 1000000)).getContent();
//
//        patrimoniosNaBaseLocal.forEach(patrimonioLocal -> {
//            if (patrimoniosNoProtheus.stream().noneMatch( patrimonioNoProtheus -> patrimonioLocal.getNumero().equals(patrimonioNoProtheus.getPlaqueta()))){
//                System.out.println(patrimonioLocal.getPlaqueta() + " não encontrado no protheus");
//                this.patrimonioRepository.deleteById(patrimonioLocal.getId());
//            }
//        });

        //
        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(
                descricaoPatrimonioFilter,
                descricaoLocalizacaoFilter,
                numeroPlaquetaFilter,
                centroCustoCodigoFilter,
                encontradoFilter,
                sobraFisicaFilter,
                transferidoFilter,
                centrosCustosCodigos == null ? null : (centrosCustosCodigos.isEmpty() ? null : centrosCustosCodigos),
                pageable);
    }

    /**
     * @param defaultFilter
     * @param centroCustoCodigoFilter
     * @param encontradoFilter
     * @param sobraFisicaFilter
     * @param transferidoFilter
     * @param pageable
     * @return
     */
    public Page<Patrimonio> listAllPatrimoniosByCentroCustoCodigoAndFilters(final String defaultFilter,
                                                                            final String centroCustoCodigoFilter,
                                                                            final Boolean encontradoFilter,
                                                                            final Boolean sobraFisicaFilter,
                                                                            final Boolean transferidoFilter,
                                                                            final Pageable pageable) {

        final Set<String> centrosCustosCodigos;
        if (!(ContextHolder.getAuthenticatedUser().isRoot() || ContextHolder.getAuthenticatedUser().getIsPatrimonio()))
            centrosCustosCodigos = ContextHolder.getAuthenticatedUser().getCentrosCusto().stream().map(CentroCusto::getCodigo).collect(Collectors.toSet());
        else
            centrosCustosCodigos = null;

        //
        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoCodigoAndFilters(
                defaultFilter,
                centroCustoCodigoFilter,
                encontradoFilter,
                sobraFisicaFilter,
                transferidoFilter,
                centrosCustosCodigos,
                pageable);
    }

    /**
     * @param centroCustoInventarioId
     * @param pageable
     * @return
     */
    Page<Patrimonio> listAllPatrimoniosByCentroCustoInventarioId(final long centroCustoInventarioId, final Pageable pageable) {
        //
        return this.patrimonioRepository.listAllPatrimoniosByCentroCustoInventarioId(centroCustoInventarioId, pageable);
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Patrimonio> listSobrasFisicasByCentroCustoCodigoAndFilters(final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        //
        return this.patrimonioRepository.listSobrasFisicasByCentroCustoCodigoAndFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, numeroPlaquetaFilter, pageable);
    }

    /**
     * @param centroCustoInventarioId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Patrimonio> listSobrasFisicasByCentroCustoInventarioId(final long centroCustoInventarioId, final Pageable pageable) {
        //
        return this.patrimonioRepository.listSobrasFisicasByCentroCustoInventarioId(centroCustoInventarioId, pageable);
    }

    /**
     * @param centroCustoCodigo
     * @param descricaoPatrimonioFilter
     * @param descricaoLocalizacaoFilter
     * @param numeroPlaquetaFilter
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<PatrimonioDTO> listPatrimoniosByCentroCustoCodigoAndFilters(final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter, final String numeroPlaquetaFilter, final Pageable pageable) {
        if (numeroPlaquetaFilter != null) {
            final List<PatrimonioDTO> patrimonioDTOS = this.findByNumero(numeroPlaquetaFilter).getContent();
            return new PageImpl<>(patrimonioDTOS.stream().filter(patrimonioDTO ->
                    (patrimonioDTO.getCentroCusto().getCodigo().equals(centroCustoCodigo) && patrimonioDTO.getCentroCustoAnterior() == null) || (patrimonioDTO.getCentroCustoAnterior() != null && patrimonioDTO.getCentroCustoAnterior().getCodigo().equals(centroCustoCodigo) && patrimonioDTO.getEncontrado())
            ).collect(Collectors.toList()));
        }

        final Page<PatrimonioDTO> patrimonioDTOS = this.patrimonioFeignRepository.listByFilters(centroCustoCodigo, descricaoPatrimonioFilter, descricaoLocalizacaoFilter, pageable);

        return getPatrimonioDTOS(patrimonioDTOS);
    }

    /**
     * @param patrimonio
     * @return
     */
    @Transactional
    public Patrimonio inserirSobraFisica(final Patrimonio patrimonio) {

        final Patrimonio patrimonioDB = this.patrimonioRepository.findByCodigoBaseAndItemAndNumero(patrimonio.getCodigoBase(), patrimonio.getItem(), patrimonio.getNumero()).orElse(new Patrimonio());

        if (patrimonioDB.getId() != null) {
            if (patrimonioDB.isEncontrado())
                throw new RuntimeException("Esse patrimônio foi inventariado em outro centro de custo (" + patrimonioDB.getCentroCustoInventario().getCentroCusto().getCodigo() + " - " + patrimonioDB.getCentroCustoInventario().getCentroCusto().getDescricao() + ")");
            else {

                patrimonioDB.setLocalizacaoAnterior(null);
                patrimonioDB.setCentroCustoAnterior(null);

                //
                patrimonioDB.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElse(patrimonio.getCentroCustoInventario()));

                patrimonioDB.setEncontrado(true);
                patrimonioDB.setSobraFisica(this.patrimonioFeignRepository.findByPlaqueta(patrimonio.getPlaqueta()).isEmpty());
                if (!patrimonioDB.isSobraFisica())
                    throw new RuntimeException("Essa plaqueta foi encontrada, não é uma sobra física!");

                return this.save(this.handlerLocalizacao(patrimonioDB, patrimonio.getLocalizacao()));
            }
        } else {

            patrimonio.setLocalizacaoAnterior(null);
            patrimonio.setCentroCustoAnterior(null);

            // TODO verificar
            patrimonio.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElse(patrimonio.getCentroCustoInventario()));

            patrimonio.getCentroCustoInventario().setCentroCusto(this.centroCustoRepository.findByCodigo(patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo()).orElse(patrimonio.getCentroCustoInventario().getCentroCusto()));
            patrimonio.setLocalizacao(this.localizacaoRepository.findByCodigo(patrimonio.getLocalizacao().getCodigo()).orElse(patrimonio.getLocalizacao()));

            patrimonio.setEncontrado(true);
            patrimonio.setSobraFisica(this.patrimonioFeignRepository.findByPlaqueta(patrimonio.getPlaqueta()).isEmpty());
            if (!patrimonio.isSobraFisica())
                throw new RuntimeException("Essa plaqueta foi encontrada, não é uma sobra física!");


            return this.save(this.handlerLocalizacao(patrimonio, patrimonio.getLocalizacao()));
        }
    }

    /**
     * @param patrimonio
     * @return
     */
    @Transactional
    public Patrimonio alterarSobraFisica(final Patrimonio patrimonio) {
        patrimonio.setSobraFisica(this.patrimonioFeignRepository.findByPlaqueta(patrimonio.getPlaqueta()).isEmpty());
        if (!patrimonio.isSobraFisica())
            throw new RuntimeException("Essa plaqueta foi encontrada, não é uma sobra física!");

        patrimonio.setLocalizacaoAnterior(null);
        patrimonio.setCentroCustoAnterior(null);

        return this.save(patrimonio);
    }

    /**
     * @param patrimonio
     * @return
     */
    @Transactional
    public Patrimonio inserirNovoPatrimonio(final Patrimonio patrimonio) {

        final Patrimonio patrimonioDB = this.patrimonioRepository.findByCodigoBaseAndItemAndNumero(patrimonio.getCodigoBase(), patrimonio.getItem(), patrimonio.getNumero()).orElse(new Patrimonio());

        if (patrimonioDB.getId() != null) {
            if (patrimonioDB.isEncontrado())
                throw new RuntimeException("Esse patrimônio foi inventariado em outro centro de custo (" + patrimonioDB.getCentroCustoInventario().getCentroCusto().getCodigo() + " - " + patrimonioDB.getCentroCustoInventario().getCentroCusto().getDescricao() + ")");
            else {

                patrimonioDB.setCentroCustoAnterior(this.handlerCentroCustoAnterior(patrimonio).getCentroCustoAnterior());

                //
                patrimonioDB.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElse(patrimonio.getCentroCustoInventario()));

//                this.handlerLocalizacao(patrimonioDB, patrimonio.getLocalizacao());

                patrimonioDB.setEncontrado(patrimonioDB.getCentroCustoAnterior() == null);
                patrimonioDB.setSobraFisica(false);

                patrimonioDB.setEncontrado(true);
                return this.save(this.handlerLocalizacao(patrimonioDB, patrimonio.getLocalizacao()));
            }
        } else {

            // TODO verificar
            patrimonio.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElse(patrimonio.getCentroCustoInventario()));

            patrimonio.setCentroCustoAnterior(this.handlerCentroCustoAnterior(patrimonio).getCentroCustoAnterior());
            patrimonio.getCentroCustoInventario().setCentroCusto(this.centroCustoRepository.findByCodigo(patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo()).orElse(patrimonio.getCentroCustoInventario().getCentroCusto()));

            patrimonio.setEncontrado(patrimonioDB.getCentroCustoAnterior() == null);
            patrimonio.setSobraFisica(false);

            patrimonioDB.setEncontrado(true);
            return this.save(this.handlerLocalizacao(patrimonio, patrimonio.getLocalizacao()));
        }
    }

    /**
     * @param patrimonio
     * @return
     */
    private Patrimonio handlerCentroCustoAnterior(final Patrimonio patrimonio) {

        final CentroCustoDTO centroCustoAneriorDTO = this.patrimonioFeignRepository.findByPlaqueta(patrimonio.getPlaqueta()).stream().findFirst().orElseThrow(() -> new RuntimeException("Plaqueta não encontrada!")).getCentroCusto();
        final ColaboradorDTO gestorDTO = centrosCustoFeignRepository.findByCentroCustoCodigo(centroCustoAneriorDTO.getCodigo(), PageRequest.of(0, 1)).getContent().stream().findFirst().orElse(new CentroCustoDTO()).getGestor();
        if (gestorDTO.getEmail() == null || gestorDTO.getNome() == null)
            throw new RuntimeException("O centro de custo atual (" + patrimonio.getCentroCustoAnterior().getCodigo() + " - " + patrimonio.getCentroCustoAnterior().getDescricao() + ") está sem gestor, este patrimônio não pode ser inventariado!");

        final CentroCusto centroCustoAnerior = new CentroCusto();
        centroCustoAnerior.setCodigo(centroCustoAneriorDTO.getCodigo());
        centroCustoAnerior.setDescricao(centroCustoAneriorDTO.getDescricao());

        // Se o usuário está na base vincula o mesmo, se não insere
        final Usuario gestorDB = this.usuarioService.findByEmailIgnoreCaseOrDocumento(gestorDTO.getEmail());
        if (gestorDB != null) {
            centroCustoAnerior.setGestor(gestorDB);
        } else {
            final Usuario gestor = new Usuario();
            gestor.setEmail(gestorDTO.getEmail());
            gestor.setNome(gestorDTO.getNome());
            centroCustoAnerior.setGestor(this.usuarioService.save(gestor));
        }

        patrimonio.setCentroCustoAnterior(this.centroCustoRepository.findByCodigo(centroCustoAnerior.getCodigo()).orElse(centroCustoAnerior));

        if (patrimonio.getCentroCustoInventario() != null && patrimonio.getCentroCustoAnterior() != null && patrimonio.getCentroCustoAnterior().getCodigo().equals(patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo()))
            patrimonio.setCentroCustoAnterior(null);

        return patrimonio;
    }

    /**
     * @param patrimonio
     */
    @Transactional
    public void encontrarFromJob(final Patrimonio patrimonio) {

        patrimonio.getCentroCustoInventario().setCentroCusto(this.centroCustoRepository.findByCodigo(patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo()).orElse(patrimonio.getCentroCustoInventario().getCentroCusto()));

        // Se não tem localização, ou o patrimônio será salvo como não encontrado, então seta a localização padrão
        if (patrimonio.getLocalizacao() == null || !patrimonio.isEncontrado()) {
            patrimonio.setLocalizacao(this.localizacaoFeignRepository.findByCodigo(Localizacao.DEFAULT_LOCATION_TO_NOT_LOCALIZATED));
        }

        patrimonio.setLocalizacao(this.localizacaoRepository.findByCodigo(patrimonio.getLocalizacao().getCodigo()).orElse(patrimonio.getLocalizacao()));

        patrimonio.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElseThrow(() -> new RuntimeException("Inventário não encontrado")));
        this.patrimonioRepository.save(patrimonio);
    }

    /**
     * @param patrimonio
     * @return
     */
    @Transactional
    public Patrimonio encontrar(final Patrimonio patrimonio) {

        final Patrimonio patrimonioDB = this.patrimonioRepository.findByCodigoBaseAndItemAndNumero(patrimonio.getCodigoBase(), patrimonio.getItem(), patrimonio.getNumero()).orElse(new Patrimonio());

        // Se o patrimônio já foi cadastrado
        if (patrimonioDB.getId() != null) {
            patrimonioDB.setEncontrado(patrimonio.isEncontrado());
            return this.save(patrimonioDB);
        }

        patrimonio.getCentroCustoInventario().setCentroCusto(this.centroCustoRepository.findByCodigo(patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo()).orElse(patrimonio.getCentroCustoInventario().getCentroCusto()));
        patrimonio.setLocalizacao(this.localizacaoRepository.findByCodigo(patrimonio.getLocalizacao().getCodigo()).orElse(patrimonio.getLocalizacao()));

        return this.save(patrimonio);
    }

    /**
     * @param patrimonio
     * @return
     */
    @Transactional
    public Patrimonio save(final Patrimonio patrimonio) {

        patrimonio.setCentroCustoInventario(this.centroCustoInventarioRepository.findById(patrimonio.getCentroCustoInventario().getId()).orElseThrow(() -> new RuntimeException("Inventário não encontrado")));

        if (patrimonio.getCentroCustoInventario().getStatus().equals(CentroCustoInventarioStatus.EM_ANALISE))
            throw new RuntimeException("A execução deste Inventário para este Centro de Custo está EM ANÁLISE");
        if (patrimonio.getCentroCustoInventario().getStatus().equals(CentroCustoInventarioStatus.APROVADO))
            throw new RuntimeException("A execução deste Inventário para este Centro de Custo já foi APROVADA");

        if (patrimonio.getCentroCustoInventario().getInventario().getDataInicio().isAfter(LocalDate.now()))
            throw new RuntimeException("O Inventário ainda não foi iniciado");

        // Handler para patrimonios inseridos atrasados
        if (patrimonio.getCentroCustoInventario().getDataTerminoExtendida() != null) {
            if (patrimonio.getCentroCustoInventario().getInventario().getDataTermino().isBefore(LocalDate.now()) && patrimonio.getId() != null && !patrimonio.isEncontrado() && this.patrimonioRepository.findById(patrimonio.getId()).orElseThrow().isEncontrado())
                throw new RuntimeException("O Inventário já foi encerrado");
            else if (patrimonio.getCentroCustoInventario().getDataTerminoExtendida().isBefore(LocalDate.now()))
                throw new RuntimeException("O Inventário já foi encerrado");
        } else if (patrimonio.getCentroCustoInventario().getInventario().getDataTermino().isBefore(LocalDate.now()))
            throw new RuntimeException("O Inventário já foi encerrado");

        return this.patrimonioRepository.save(patrimonio);
    }

    /**
     * @param patrimonioId
     * @param novaLocalizacao
     * @return
     */
    @Transactional
    public Patrimonio alterarLocalizacao(final long patrimonioId, final Localizacao novaLocalizacao) {
        final Patrimonio patrimonioDB = this.patrimonioRepository.findById(patrimonioId).orElseThrow();
        return this.save(handlerLocalizacao(patrimonioDB, novaLocalizacao));
    }

    /**
     * @param patrimonio
     * @param novaLocalizacao
     * @return
     */
    private Patrimonio handlerLocalizacao(@NotNull final Patrimonio patrimonio, @NotNull final Localizacao novaLocalizacao) {

        // Insiro a localização tampão na base local, caso ela não exista
        Localizacao localizacaoPadraoParaNaoEncontrados = this.localizacaoRepository.findByCodigo(Localizacao.DEFAULT_LOCATION_TO_NOT_LOCALIZATED).orElse(null);
        if (localizacaoPadraoParaNaoEncontrados == null)
            localizacaoPadraoParaNaoEncontrados = this.localizacaoRepository.save(this.localizacaoFeignRepository.findByCodigo(Localizacao.DEFAULT_LOCATION_TO_NOT_LOCALIZATED));

        if (novaLocalizacao != null)
            patrimonio.setLocalizacao(this.localizacaoRepository.findByCodigo(novaLocalizacao.getCodigo()).orElse(novaLocalizacao));
        else
            patrimonio.setLocalizacao(localizacaoPadraoParaNaoEncontrados);

        final Localizacao localizacaoProtheusOuAnterior = this.patrimonioFeignRepository.findByPlaqueta(patrimonio.getPlaqueta()).stream().findFirst().orElse(new PatrimonioDTO(patrimonio.getLocalizacao())).getLocalizacao();
        if (localizacaoProtheusOuAnterior == null)
            patrimonio.setLocalizacaoAnterior(this.localizacaoRepository.findByCodigo(localizacaoPadraoParaNaoEncontrados.getCodigo()).orElse(localizacaoPadraoParaNaoEncontrados));
        else
            patrimonio.setLocalizacaoAnterior(this.localizacaoRepository.findByCodigo(localizacaoProtheusOuAnterior.getCodigo()).orElse(localizacaoProtheusOuAnterior));

        if (patrimonio.getLocalizacao() != null && patrimonio.getLocalizacaoAnterior() != null && patrimonio.getLocalizacao().getCodigo().equals(patrimonio.getLocalizacaoAnterior().getCodigo()))
            patrimonio.setLocalizacaoAnterior(null);

        return patrimonio;
    }

    /**
     * @param descricaoLocalizacaoFilter
     * @return
     */
    public Set<Localizacao> listByLocalizacoesFilters(final String centroCustoCodigo, final String descricaoPatrimonioFilter, final String descricaoLocalizacaoFilter) {

        try {
            final Localizacao localizacao = this.localizacaoFeignRepository.findByCodigo(descricaoLocalizacaoFilter);
            if (localizacao != null) {
                return new HashSet<>(Collections.singletonList(localizacao));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashSet<>(this.localizacaoFeignRepository.listLocalizacoesByDescricao(descricaoLocalizacaoFilter, PageRequest.of(0, 1000000)).getContent());
    }

    /**
     * @param numero
     * @return
     */
    public Page<PatrimonioDTO> findByNumero(final String numero) {

        final Page<PatrimonioDTO> patrimonioDTOS = this.patrimonioFeignRepository.findByPlaqueta(numero);

        return getPatrimonioDTOS(patrimonioDTOS);
    }

    /**
     * @param patrimonioDTOS
     * @return
     */
    @Transactional(readOnly = true)
    Page<PatrimonioDTO> getPatrimonioDTOS(final Page<PatrimonioDTO> patrimonioDTOS) {
        patrimonioDTOS.forEach(patrimonioDTO -> {
            final Optional<Patrimonio> patrimonio = this.patrimonioRepository.findByCodigoBaseAndItemAndNumero(patrimonioDTO.getCodigoBase(), patrimonioDTO.getItem(), patrimonioDTO.getPlaqueta());
            if (patrimonio.isPresent()) {
                patrimonioDTO.setEncontrado(patrimonio.get().isEncontrado());
                patrimonioDTO.setId(patrimonio.get().getId());

                if (patrimonio.get().getLocalizacao() != null)
                    patrimonioDTO.setLocalizacao(patrimonio.get().getLocalizacao());

                patrimonioDTO.setLocalizacaoAnterior(patrimonio.get().getLocalizacaoAnterior());

                patrimonioDTO.setCentroCustoInventario(patrimonio.get().getCentroCustoInventario());

                patrimonioDTO.setCentroCustoAnterior(patrimonio.get().getCentroCustoAnterior());
            }

        });

        return patrimonioDTOS;
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<Patrimonio> findById(final long id) {
        return this.patrimonioRepository.findById(id);
    }

    /**
     * Cria o arquivo de exportação
     *
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = true)
    public byte[] export(final String defaultFilter,
                         final String centroCustoCodigoFilter,
                         final Boolean encontradoFilter,
                         final Boolean sobraFisicaFilter,
                         final Boolean transferidoFilter,
                         final Pageable pageable) throws IOException {

        if (!ContextHolder.getAuthenticatedUser().getIsPatrimonio() && !ContextHolder.getAuthenticatedUser().isRoot())
            throw new RuntimeException("Você não tem permissão para isso!");

        try (
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                final CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'));
        ) {

            final List<Patrimonio> patrimoniosTransferidos = this.listAllPatrimoniosByCentroCustoCodigoAndFilters(defaultFilter, centroCustoCodigoFilter, encontradoFilter, sobraFisicaFilter, transferidoFilter, pageable).getContent();
            patrimoniosTransferidos.forEach(patrimonio -> {
                if (patrimonio.getCentroCustoInventario().getStatus().equals(CentroCustoInventarioStatus.APROVADO) || patrimonio.getCentroCustoInventario().getStatus().equals(CentroCustoInventarioStatus.FINALIZADO))
                    try {

                        final String localizacaoCodigo = (patrimonio.getLocalizacaoAnterior() != null || patrimonio.getCentroCustoAnterior() != null) ? patrimonio.getLocalizacao().getCodigo() : Localizacao.DEFAULT_LOCATION_TO_NOT_LOCALIZATED;

                        if (patrimonio.getItem() == null || patrimonio.getCodigoBase() == null) {
                            final PatrimonioDTO patrimonioDTO = this.findByNumero(patrimonio.getNumero()).getContent().stream().findFirst().orElse(null);
                            csvPrintRecord(patrimonio, csvPrinter, localizacaoCodigo, patrimonioDTO);
                        } else if (patrimonio.getItem() != null && patrimonio.getCodigoBase() != null)
                            csvPrinter.printRecord(patrimonio.getCodigoBase(), patrimonio.getItem(), patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo(), localizacaoCodigo);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            });

            csvPrinter.flush();
            return out.toByteArray();

        }
    }

    /**
     * @param patrimonio
     * @param csvPrinter
     * @param localizacaoCodigo
     * @param patrimonioDTO
     * @throws IOException
     */
    static void csvPrintRecord(final Patrimonio patrimonio, final CSVPrinter csvPrinter, final String localizacaoCodigo, final PatrimonioDTO patrimonioDTO) throws IOException {
        if (patrimonioDTO != null && patrimonioDTO.getItem() != null && patrimonioDTO.getCodigoBase() != null) {
            patrimonio.setCodigoBase(patrimonioDTO.getCodigoBase());
            patrimonio.setItem(patrimonioDTO.getItem());
            csvPrinter.printRecord(patrimonio.getCodigoBase(), patrimonio.getItem(), patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo(), localizacaoCodigo);
        }
    }

    /**
     * @param id
     */
    @Transactional
    public void delete(final long id) {
        this.patrimonioRepository.deleteById(id);
    }
}
