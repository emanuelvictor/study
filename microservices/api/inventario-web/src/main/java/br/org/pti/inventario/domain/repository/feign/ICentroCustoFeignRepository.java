package br.org.pti.inventario.domain.repository.feign;

import br.org.pti.inventario.domain.entity.patrimonio.dto.LocalizacaoDTO;
import br.org.pti.inventario.domain.entity.pessoal.dto.CentroCustoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@FeignClient(name = "centros-custo", url = "${oauth.endpoints.centros-custo}")
public interface ICentroCustoFeignRepository {

    /**
     * @param descricao String
     * @param pageable  Pageable
     * @return Page<CentroCustoDTO>
     */
    @GetMapping("/porDescricaoContendo")
    Page<CentroCustoDTO> listByFilters(@RequestParam("descricao") final String descricao, final Pageable pageable);

    /**
     * @param centroCustoCodigo String
     * @param pageable          Pageable
     * @return Page<CentroCustoDTO>
     */
    @GetMapping("/comGestores")
    Page<CentroCustoDTO> findByCentroCustoCodigo(@RequestParam("centrocusto") final String centroCustoCodigo, final Pageable pageable);

    /**
     * @param centroCustoCodigo String
     * @return Page<CentroCustoDTO>
     */
    @GetMapping("{centroCustoCodigo}")
    Optional<CentroCustoDTO> findByCentroCustoCodigo(@PathVariable("centroCustoCodigo") final String centroCustoCodigo);

}
