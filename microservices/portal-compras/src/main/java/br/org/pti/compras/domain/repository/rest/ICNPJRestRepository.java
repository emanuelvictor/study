package br.org.pti.compras.domain.repository.rest;

import br.org.pti.compras.domain.repository.dtos.FornecedorWsExterno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fornecedores-ws-externo", url = "${oauth.endpoints.fornecedores-ws-externo}")
public interface ICNPJRestRepository {

    @GetMapping(value = "/{cnpj}"/*, headers = {"Access-Control-Allow-Origin", "*"}*/)
    FornecedorWsExterno findFornecedorWsExternoByCNPJ(@PathVariable final String cnpj);

}
