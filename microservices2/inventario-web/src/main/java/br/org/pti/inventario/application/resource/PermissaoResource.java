package br.org.pti.inventario.application.resource;

import br.org.pti.inventario.domain.entity.configuracao.Permissao;
import br.org.pti.inventario.domain.repository.IPermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.org.pti.inventario.application.resource.Roles.PERMISSAO_MAPPING_RESOURCE;

/**
 * RESTFul de Permissões
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({PERMISSAO_MAPPING_RESOURCE, "/sistema/" + PERMISSAO_MAPPING_RESOURCE})
public class PermissaoResource {

    /**
     *
     */
    private final IPermissaoRepository permissaoRepository;

    /**
     * @param defaultFilter String
     * @param branch        Boolean
     * @param pageable      Pageable
     * @return Page<Permissao>
     */
    @GetMapping
    public Page<Permissao> listByFilters(final String defaultFilter, final Boolean branch, final Pageable pageable) {
        return this.permissaoRepository.listByFilters(defaultFilter, branch, pageable);
    }

}
