package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.configuracao.Permissao;
import br.org.pti.compras.domain.repository.IPermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.org.pti.compras.application.resource.Roles.PERMISSAO_MAPPING_RESOURCE;

@RestController
@RequiredArgsConstructor
@RequestMapping({PERMISSAO_MAPPING_RESOURCE, "/sistema/" + PERMISSAO_MAPPING_RESOURCE})
public class PermissaoResource {


    private final IPermissaoRepository permissaoRepository;

    @GetMapping
    public Page<Permissao> listByFilters(final String defaultFilter, final Boolean branch, final Pageable pageable) {
        return this.permissaoRepository.listByFilters(defaultFilter, branch, pageable);
    }

}
