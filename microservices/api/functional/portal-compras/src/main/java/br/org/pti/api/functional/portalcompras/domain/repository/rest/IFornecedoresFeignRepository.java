package br.org.pti.api.functional.portalcompras.domain.repository.rest;

import br.org.pti.api.functional.portalcompras.domain.repository.dtos.FornecedorProtheus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "fornecedores", url = "${oauth.endpoints.fornecedores}")
public interface IFornecedoresFeignRepository {

    @PostMapping
    String save(@RequestBody final FornecedorProtheus fornecedor);

    @PutMapping
    void update(@RequestBody final FornecedorProtheus fornecedor);

    @GetMapping("{documento}/porDocumento")
    ResponseEntity<Map<Object, Object>> findByDocumento(@PathVariable final String documento);
}
