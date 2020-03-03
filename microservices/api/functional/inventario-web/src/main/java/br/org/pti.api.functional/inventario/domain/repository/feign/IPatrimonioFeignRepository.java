package br.org.pti.inventario.domain.repository.feign;

import br.org.pti.inventario.domain.entity.patrimonio.dto.PatrimonioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@FeignClient(name = "patrimonios", url = "${oauth.endpoints.patrimonios}")
public interface IPatrimonioFeignRepository {

    /**
     *
     * @param centroCusto
     * @param descricaoPatrimonio
     * @param descricaoLocalizacao
     * @param pageable
     * @return
     */
    @GetMapping("/{centroCusto}/porCentroCusto")
    Page<PatrimonioDTO> listByFilters(@PathVariable("centroCusto") final String centroCusto, @RequestParam("descricaoPatrimonio") final String descricaoPatrimonio, @RequestParam("descricaoLocalizacao") final String descricaoLocalizacao, final Pageable pageable);

    /**
     * @param plaqueta
     * @return
     */
    @GetMapping("/{plaqueta}")
    Page<PatrimonioDTO> findByPlaqueta(@PathVariable("plaqueta") final String plaqueta);

}
