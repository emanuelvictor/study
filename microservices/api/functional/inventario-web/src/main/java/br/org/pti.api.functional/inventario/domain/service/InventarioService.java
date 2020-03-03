package br.org.pti.inventario.domain.service;

import br.org.pti.inventario.application.context.ContextHolder;
import br.org.pti.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.inventario.domain.entity.patrimonio.Localizacao;
import br.org.pti.inventario.domain.entity.patrimonio.Patrimonio;
import br.org.pti.inventario.domain.entity.patrimonio.dto.PatrimonioDTO;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventarioStatus;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.Inventario;
import br.org.pti.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.inventario.domain.repository.ICentroCustoInventarioRepository;
import br.org.pti.inventario.domain.repository.ICentroCustoRepository;
import br.org.pti.inventario.domain.repository.IInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class InventarioService {

    /**
     *
     */
    private final UsuarioService usuarioService;

    /**
     *
     */
    private final PatrimonioService patrimonioService;

    /**
     *
     */
    private final IInventarioRepository inventarioRepository;

    /**
     *
     */
    private final ICentroCustoRepository centroCustoRepository;

    /**
     *
     */
    private final ICentroCustoInventarioRepository centroCustoInventarioRepository;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<Inventario>
     */
    public Page<Inventario> listByFilters(final String defaultFilter, final Pageable pageable) {

        // Se o usuário for do patrimônio ou for root, tráz todos os inventários
        return this.inventarioRepository.listByFilters(defaultFilter, ContextHolder.getAuthenticatedUser().isRoot() || ContextHolder.getAuthenticatedUser().getIsPatrimonio() ? 0 : ContextHolder.getAuthenticatedUser().getId(), pageable);
    }

    /**
     * @param id long
     * @return Inventario
     */
    @Transactional(readOnly = true)
    public Inventario findById(final long id) {
        return this.inventarioRepository.findById(id).orElseThrow();
    }

    /**
     * @param inventarioId      long
     * @param centroCustoCodigo String
     * @return CentroCustoInventario
     */
    public CentroCustoInventario findByInventarioIdAndCentroCustoCodigo(final long inventarioId, final String centroCustoCodigo) {
        return this.centroCustoInventarioRepository.findByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);
    }

    /**
     * @param inventario Inventario
     * @return Inventario
     */
    @Transactional
    public Inventario save(final Inventario inventario) {

        inventario.getCentrosCusto().forEach(centroCustoInventario -> {

            centroCustoInventario.setInventario(inventario);

            // Se o gestor já está cadastrado, vincula o mesmo. Se não cria um novo.
            final Usuario gestor = this.usuarioService.findByEmailIgnoreCaseOrDocumento(centroCustoInventario.getCentroCusto().getGestor().getEmail());
            if (gestor == null)
                centroCustoInventario.getCentroCusto().setGestor(this.usuarioService.save(centroCustoInventario.getCentroCusto().getGestor()));
            else
                centroCustoInventario.getCentroCusto().getGestor().setId(gestor.getId());

            // Se o centro de custo já está cadastrado, vincula o mesmo. Se não cria um novo.
            final Optional<CentroCusto> centroCustoOptional = this.centroCustoRepository.findByCodigo(centroCustoInventario.getCentroCusto().getCodigo());
            centroCustoOptional.ifPresentOrElse(
                    centroCustoInventario::setCentroCusto,
                    () -> centroCustoInventario.setCentroCusto(this.centroCustoRepository.save(centroCustoInventario.getCentroCusto())));

            if (centroCustoInventario.getExecutores() != null && centroCustoInventario.getExecutores().size() > 0)
                centroCustoInventario.getExecutores().forEach(executor -> {

                    executor.setCentroCustoInventario(centroCustoInventario);

                    // Se o executor já está cadastrado, vincula o mesmo. Se não cria um novo.
                    final Usuario usuario = this.usuarioService.findByEmailIgnoreCaseOrDocumento(executor.getUsuario().getEmail());
                    if (usuario == null)
                        executor.setUsuario(this.usuarioService.save(executor.getUsuario()));
                    else
                        executor.getUsuario().setId(usuario.getId());

                });

        });

        return this.inventarioRepository.save(inventario);
    }

    /**
     * @param id         long
     * @param inventario Inventario
     * @return Inventario
     */
    public Inventario save(final long id, final Inventario inventario) {
        inventario.setId(id);
        return this.save(inventario);
    }

    /**
     * @param id Long
     * @return Boolean
     */
    public Boolean delete(final long id) {
        this.inventarioRepository.deleteById(id);
        return true;
    }

    /**
     * @param inventarioId
     * @param centroCustoCodigo
     * @return
     */
    @Transactional
    public CentroCustoInventario finalizar(final long inventarioId, final String centroCustoCodigo) {
        final CentroCustoInventario centroCustoInventario = this.findByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);

        if (!ContextHolder.getAuthenticatedUser().isRoot() && !ContextHolder.getAuthenticatedUser().getIsPatrimonio())
            throw new RuntimeException("Essa funcionalidade é de uso exclusivo do patrimônio!");

        Assert.isTrue(!centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.FINALIZADO), "Já está finalizado!");
        centroCustoInventario.setStatus(CentroCustoInventarioStatus.FINALIZADO);
        return this.centroCustoInventarioRepository.save(centroCustoInventario);
    }

    /**
     * Rollback pra suprir as cabacices dos usuários
     *
     * @param inventarioId
     * @param centroCustoCodigo
     * @return
     */
    @Transactional
    public CentroCustoInventario desfinalizar(final long inventarioId, final String centroCustoCodigo) {
        final CentroCustoInventario centroCustoInventario = this.findByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);

        if (!ContextHolder.getAuthenticatedUser().isRoot() && !ContextHolder.getAuthenticatedUser().getIsPatrimonio())
            throw new RuntimeException("Essa funcionalidade é de uso exclusivo do patrimônio!");

        Assert.isTrue(centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.FINALIZADO), "Não finalizado!");
        centroCustoInventario.setStatus(CentroCustoInventarioStatus.APROVADO);
        return this.centroCustoInventarioRepository.save(centroCustoInventario);
    }

    /**
     * Cria o arquivo de exportação
     *
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = true)
    public byte[] exportar(final long inventarioId, final String centroCustoCodigo) throws IOException {

        try (
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                final CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'));
        ) {

            final CentroCustoInventario centroCustoInventario = getCentroCustoInventarioByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);

            final List<Patrimonio> patrimoniosTransferidos = this.patrimonioService.listAllPatrimoniosByCentroCustoInventarioId(centroCustoInventario.getId(), null).getContent();
            patrimoniosTransferidos.forEach(patrimonio -> {
                if ((patrimonio.getLocalizacaoAnterior() != null || patrimonio.getCentroCustoAnterior() != null) || !patrimonio.isEncontrado())
                    try {

                        final String localizacaoCodigo = (patrimonio.getLocalizacaoAnterior() != null || patrimonio.getCentroCustoAnterior() != null) ? patrimonio.getLocalizacao().getCodigo() : Localizacao.DEFAULT_LOCATION_TO_NOT_LOCALIZATED;

                        if (patrimonio.getItem() == null || patrimonio.getCodigoBase() == null) {
                            final PatrimonioDTO patrimonioDTO = patrimonioService.findByNumero(patrimonio.getNumero()).getContent().stream().findFirst().orElse(null);
                            PatrimonioService.csvPrintRecord(patrimonio, csvPrinter, localizacaoCodigo, patrimonioDTO);
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
     * Cria o arquivo de exportação das sobras físicas
     *
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = true)
    public byte[] exportarSobrasFisicas(final long inventarioId, final String centroCustoCodigo) throws IOException {

        try (
                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
                final CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'));
        ) {

            final CentroCustoInventario centroCustoInventario = getCentroCustoInventarioByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);

            final List<Patrimonio> patrimoniosTransferidos = this.patrimonioService.listSobrasFisicasByCentroCustoInventarioId(centroCustoInventario.getId(), null).getContent();
            patrimoniosTransferidos.forEach(patrimonio -> {
                try {

                    final String localizacaoCodigo = patrimonio.getLocalizacao().getCodigo();

                    csvPrinter.printRecord(patrimonio.getNumero(), "001", patrimonio.getCentroCustoInventario().getCentroCusto().getCodigo(), localizacaoCodigo, patrimonio.getMarca() != null ? patrimonio.getMarca() : "", patrimonio.getModelo() != null ? patrimonio.getModelo() : "", patrimonio.getDescricao() != null ? patrimonio.getDescricao() : "");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            csvPrinter.flush();
            return out.toByteArray();

        }
    }

    /**
     * @param inventarioId
     * @param centroCustoCodigo
     * @return
     */
    private CentroCustoInventario getCentroCustoInventarioByInventarioIdAndCentroCustoCodigo(final long inventarioId, final String centroCustoCodigo) {
        final CentroCustoInventario centroCustoInventario = this.findByInventarioIdAndCentroCustoCodigo(inventarioId, centroCustoCodigo);

        if ((!ContextHolder.getAuthenticatedUser().getIsPatrimonio() && !ContextHolder.getAuthenticatedUser().isRoot()) && !centroCustoInventario.getCentroCusto().getGestor().getId().equals(ContextHolder.getAuthenticatedUser().getId()))
            throw new RuntimeException("Você não tem permissão para isso!");

        Assert.isTrue(centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.FINALIZADO) || centroCustoInventario.getStatus().equals(CentroCustoInventarioStatus.APROVADO), "Conclua o inventário primeiramente!");

        return centroCustoInventario;
    }
}
