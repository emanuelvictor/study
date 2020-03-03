package br.org.pti.api.functional.portalcompras.domain.repository.rest;

import br.org.pti.api.functional.portalcompras.domain.repository.dtos.Cidade;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cidades", url = "${oauth.endpoints.cidades}")
public interface ICidadesFeignRepository {

    @GetMapping("/{uf}/porEstado")
    List<Cidade> listByFilters(@PathVariable("uf") final String uf);

}
