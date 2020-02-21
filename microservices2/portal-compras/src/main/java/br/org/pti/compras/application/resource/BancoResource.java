package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.cadastros.Banco;
import br.org.pti.compras.domain.repository.rest.IBancosFeignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static br.org.pti.compras.application.resource.Roles.BANCO_MAPPING_RESOURCE;
import static br.org.pti.compras.infrastructure.aid.Utils.normalizeSymbolsAndAccents;

//import org.springframework.security.oauth2.client.OAuth2RestOperations;

@RestController
@RequiredArgsConstructor
@RequestMapping({BANCO_MAPPING_RESOURCE, "/sistema/" + BANCO_MAPPING_RESOURCE})
public class BancoResource {

    //    private final IBancoRestRepository bancoRepository;
    private final IBancosFeignRepository bancoFeignRepository;

    @GetMapping
    public List<Banco> listByFilters(final String defaultFilter) {
        return this.bancoFeignRepository.listByFilters()
                .parallelStream()
                .filter(banco -> defaultFilter == null || ((normalizeSymbolsAndAccents(banco.getNome().toLowerCase()).contains(normalizeSymbolsAndAccents(defaultFilter.toLowerCase())) || banco.getCodigo().toLowerCase().contains(normalizeSymbolsAndAccents(defaultFilter.toLowerCase())))))
                .collect(Collectors.toList());
    }

}
