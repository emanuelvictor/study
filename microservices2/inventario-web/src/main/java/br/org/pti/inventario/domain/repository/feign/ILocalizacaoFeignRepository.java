package br.org.pti.inventario.domain.repository.feign;

import br.org.pti.inventario.domain.entity.patrimonio.Localizacao;
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
@FeignClient(name = "localizacoes", url = "${oauth.endpoints.localizacoes}")
public interface ILocalizacaoFeignRepository {

    /**
     * @param descricao
     * @param pageable
     * @return
     */
    @GetMapping("/{descricao}/porDescricao")
    Page<Localizacao> listLocalizacoesByDescricao(@PathVariable("descricao") final String descricao, final Pageable pageable);

    /**
     *
     * @param codigo
     * @return
     */
    @GetMapping("/{codigo}")
    Localizacao findByCodigo(@PathVariable("codigo") final String codigo);
}
