package br.org.pti.inventario.domain.repository.feign;

import br.org.pti.inventario.domain.entity.pessoal.dto.ColaboradorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@FeignClient(name = "colaboradores", url = "${oauth.endpoints.colaboradores}")
public interface IColaboradorFeignRepository {

    /**
     * @param centroCusto String
     * @param pageable    Pageable
     * @return Page<ColaboradorDTO>
     */
    @GetMapping("/{centroCusto}/porCentroCusto")
    Page<ColaboradorDTO> listColaboradoresByCentroCustoCodigo(@PathVariable("centroCusto") final String centroCusto, final Pageable pageable);

}
