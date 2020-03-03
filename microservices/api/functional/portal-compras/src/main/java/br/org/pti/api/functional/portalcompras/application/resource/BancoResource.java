package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.repository.rest.IBancosFeignRepository;
import br.org.pti.api.functional.portalcompras.infrastructure.aid.Utils;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Banco;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//import org.springframework.security.oauth2.client.OAuth2RestOperations;

@RestController
@RequiredArgsConstructor
@RequestMapping({Roles.BANCO_MAPPING_RESOURCE, "/sistema/" + Roles.BANCO_MAPPING_RESOURCE})
public class BancoResource {

    //    private final IBancoRestRepository bancoRepository;
    private final IBancosFeignRepository bancoFeignRepository;

    @GetMapping
    public List<Banco> listByFilters(final String defaultFilter) {
        return this.bancoFeignRepository.listByFilters()
                .parallelStream()
                .filter(banco -> defaultFilter == null || ((Utils.normalizeSymbolsAndAccents(banco.getNome().toLowerCase()).contains(Utils.normalizeSymbolsAndAccents(defaultFilter.toLowerCase())) || banco.getCodigo().toLowerCase().contains(Utils.normalizeSymbolsAndAccents(defaultFilter.toLowerCase())))))
                .collect(Collectors.toList());
    }

}
