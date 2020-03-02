package br.org.pti.compras.domain.repository.rest;

import br.org.pti.compras.domain.entity.cadastros.Banco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "bancos", url = "${oauth.endpoints.bancos}")
public interface IBancosFeignRepository {

    @GetMapping
    List<Banco> listByFilters();

}
