package br.org.pti.api.functional.accountmanager.application.resource;

import br.org.pti.api.functional.accountmanager.domain.entities.Permission;
import br.org.pti.api.functional.accountmanager.domain.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * RESTFul de Permissões
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/permissions")
//@RequestMapping({PERMISSAO_MAPPING_RESOURCE, "/sistema/" + PERMISSAO_MAPPING_RESOURCE})
public class PermissionResource {

    /**
     *
     */
    private final PermissionRepository permissionRepository;

    /**
     * @param defaultFilter String
     * @param branch        Boolean
     * @param pageable      Pageable
     * @return Page<Permission>
     */
    @GetMapping
    public Page<Permission> listByFilters(final String defaultFilter, final Boolean branch, final Pageable pageable) {
        return this.permissionRepository.listByFilters(defaultFilter, branch, pageable);
    }

}
