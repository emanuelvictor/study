package br.org.pti.api.functional.inventario.domain.service;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import br.org.pti.api.functional.inventario.application.i18n.MessageSourceHolder;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.dto.CentroCustoDTO;
import br.org.pti.api.functional.inventario.domain.repository.ICentroCustoRepository;
import br.org.pti.api.functional.inventario.domain.repository.IUsuarioRepository;
import br.org.pti.api.functional.inventario.domain.repository.feign.ICentroCustoFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class CentroCustoService {

    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;

    /**
     *
     */
    private final ICentroCustoRepository centroCustoRepository;

    /**
     *
     */
    private final ICentroCustoFeignRepository centrosCustoFeignRepository;

    /**
     * @param descricaoFilter String
     * @param pageable        Pageable
     * @return Page<CentroCusto>
     */
    public Page<CentroCustoDTO> listByFilters(final String descricaoFilter, final String centroCustoCodigoFilter, final Pageable pageable) {

        final Usuario usuarioAutenticado = (Usuario) usuarioRepository.findByEmailIgnoreCaseOrDocumento(ContextHolder.getAuthenticatedUser().getEmail());

        if (!usuarioAutenticado.isRoot() && !usuarioAutenticado.getIsPatrimonio()) {

            final List<CentroCustoDTO> centrosCustoList = new ArrayList<>();

            if (usuarioAutenticado.getIsGestor())
                centrosCustoList.addAll(usuarioAutenticado.getCentrosCusto().stream().map(centroCusto -> {
                    final CentroCustoDTO centroCustoDTO = new CentroCustoDTO();
                    centroCustoDTO.setCodigo(centroCusto.getCodigo());
                    centroCustoDTO.setDescricao(centroCusto.getDescricao());
                    centroCustoDTO.setEmailGestor(centroCusto.getGestor().getEmail());
                    centroCustoDTO.setNomeGestor(centroCusto.getGestor().getNome());
                    return centroCustoDTO;
                }).collect(Collectors.toList()));

            if (usuarioAutenticado.getIsExecutor())
                centrosCustoList.addAll(usuarioAutenticado.getExecutores().stream().map(executor -> {
                    final CentroCustoDTO centroCustoDTO = new CentroCustoDTO();
                    centroCustoDTO.setCodigo(executor.getCentroCustoInventario().getCentroCusto().getCodigo());
                    centroCustoDTO.setDescricao(executor.getCentroCustoInventario().getCentroCusto().getDescricao());
                    centroCustoDTO.setEmailGestor(executor.getCentroCustoInventario().getCentroCusto().getGestor().getEmail());
                    centroCustoDTO.setNomeGestor(executor.getCentroCustoInventario().getCentroCusto().getGestor().getNome());
                    return centroCustoDTO;
                }).collect(Collectors.toList()));

            centrosCustoList.forEach(centroCusto -> {
                final CentroCustoDTO centroCustoDTOComGestor = centrosCustoFeignRepository.findByCentroCustoCodigo(centroCusto.getCodigo(), PageRequest.of(0, 1)).getContent().stream().findFirst().orElse(new CentroCustoDTO());
                centroCusto.setEmailGestor(centroCustoDTOComGestor.getEmailGestor());
                centroCusto.setNomeGestor(centroCustoDTOComGestor.getNomeGestor());
            });

            if (centroCustoCodigoFilter != null)
                return new PageImpl<>(centrosCustoList.stream().filter(centroCustoDTO -> centroCustoDTO.getCodigo().equals(centroCustoCodigoFilter)).collect(Collectors.toList()));

            return new PageImpl<>(centrosCustoList);
        }

        final Page<CentroCustoDTO> centrosCusto;
        if (centroCustoCodigoFilter != null)
            centrosCusto = this.centrosCustoFeignRepository.findByCentroCustoCodigo(centroCustoCodigoFilter, pageable);
        else
            centrosCusto = this.centrosCustoFeignRepository.listByFilters(descricaoFilter, pageable);

        centrosCusto.forEach(centroCusto -> {
            final CentroCustoDTO centroCustoDTOComGestor = centrosCustoFeignRepository.findByCentroCustoCodigo(centroCusto.getCodigo(),  PageRequest.of(0, 1)).getContent().stream().findFirst().orElse(new CentroCustoDTO());
            centroCusto.setEmailGestor(centroCustoDTOComGestor.getEmailGestor());
            centroCusto.setNomeGestor(centroCustoDTOComGestor.getNomeGestor());
        });

        return centrosCusto;
    }

    /**
     * @param codigo String
     * @return CentroCusto
     */
    public CentroCustoDTO findByCodigo(final String codigo) {
        return this.centrosCustoFeignRepository.findByCentroCustoCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", codigo)));
    }

    /**
     * @param id long
     * @return CentroCusto
     */
    public CentroCusto findCentroCustoInventarioById(final long id) {
        return this.centroCustoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MessageSourceHolder.getMessage("repository.notFoundById", id)));
    }

    /**
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<CentroCusto> findById(final Long id) {
        return this.centroCustoRepository.findById(id);
    }

    /**
     * @param gestorId
     * @return
     */
    @Transactional(readOnly = true)
    public Set<CentroCusto> findByGestorId(final long gestorId) {
        return this.centroCustoRepository.findByGestorId(gestorId);
    }

    /**
     *
     * @param email
     * @return
     */
    public Set<CentroCusto> findByGestorEmail(final String email) {
        return this.centroCustoRepository.findByGestorEmail(email);
    }
}
