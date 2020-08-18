package br.org.pti.api.functional.accountmanager.domain.services;

import br.org.pti.api.functional.accountmanager.domain.entities.AccessGroup;
import br.org.pti.api.functional.accountmanager.domain.entities.AccessGroupPermission;
import br.org.pti.api.functional.accountmanager.domain.repositories.AccessGroupPermissionRepository;
import br.org.pti.api.functional.accountmanager.domain.repositories.AccessGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 04/01/2020
 */
@Component
@RequiredArgsConstructor
public class AccessGroupService {

    /**
     *
     */
    private final AccessGroupRepository accessGroupRepository;

    /**
     *
     */
    private final AccessGroupPermissionRepository accessGroupPermissionRepository;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<AccessGroup>
     */
    public Page<AccessGroup> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.accessGroupRepository.listByFilters(defaultFilter, pageable);
    }

    /**
     * @param id long
     * @return {@link Optional <AccessGroup>}
     */
    public Optional<AccessGroup> findById(final long id) {
        return this.accessGroupRepository.findById(id);
    }

    /**
     * @param accessGroup {@link AccessGroup}
     * @return {@link AccessGroup}
     */
    @Transactional
    public AccessGroup save(final AccessGroup accessGroup) {
        Assert.notEmpty(accessGroup.getAccessGroupPermissions(), "Defina permissões para esse Grupo de Acesso");
        return accessGroupRepository.save(accessGroup);
    }

    /**
     * @param id          long
     * @param accessGroup {@link AccessGroup}
     * @return {@link AccessGroup}
     */
    public AccessGroup save(final long id, final AccessGroup accessGroup) {
        accessGroup.setId(id);

        Assert.notEmpty(accessGroup.getAccessGroupPermissions(), "Defina permissões para esse Grupo de Acesso");

        /*
         * Lista auxiliar com os grupos acesso permissao que serão persistidos a posteriore
         */
        final Set<AccessGroupPermission> accessGroupPermissionList = accessGroup.getAccessGroupPermissions();

        /*
         * Seto null nos grupos acesso permissoes, dessa forma o entityManager remove todos via cascade
         */
        accessGroup.setAccessGroupPermissions(new HashSet<>());

        /*
         * Atualizo o grupo de acesso
         */
        this.accessGroupRepository.save(accessGroup);

        /*
         * Insiro todos os grupo acesso permissao
         */
        accessGroupPermissionList.forEach(accessGroupPermission -> {
            if (accessGroupPermission.getId() == null)
                accessGroupPermission.setId(null);
            this.accessGroupPermissionRepository.save(accessGroupPermission);
        });

        return accessGroup;
    }

    /**
     * @param id Long
     * @return Boolean
     */
    public Boolean delete(final long id) {
        this.accessGroupRepository.deleteById(id);
        return true;
    }
}
