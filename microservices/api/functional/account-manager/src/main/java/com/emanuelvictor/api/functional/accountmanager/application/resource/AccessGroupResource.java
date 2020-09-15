package com.emanuelvictor.api.functional.accountmanager.application.resource;

import com.emanuelvictor.api.functional.accountmanager.domain.entities.AccessGroup;
import com.emanuelvictor.api.functional.accountmanager.domain.services.AccessGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.emanuelvictor.api.functional.accountmanager.application.resource.Roles.*;

/**
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({ACCESS_GROUP_MAPPING_RESOURCE})
public class AccessGroupResource {

    /**
     *
     */
    private final AccessGroupService accessGroupService;

    /**
     * @param defaultFilter String
     * @param pageable      Pageable
     * @return Page<AccessGroup>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + ACCESS_GROUP_GET_ROLE + "')")
    public Page<AccessGroup> listByFilters(final String defaultFilter, final Pageable pageable) {
        return this.accessGroupService.listByFilters(defaultFilter, pageable);
    }

    /**
     * @param id long
     * @return Optional<AccessGroup>
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + ACCESS_GROUP_GET_ROLE + "')")
    public Optional<AccessGroup> findById(@PathVariable final long id) {
        return this.accessGroupService.findById(id);
    }

    /**
     * @param grupoAcesso AccessGroup
     * @return AccessGroup
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + ACCESS_GROUP_POST_ROLE + "')")
    public AccessGroup save(@RequestBody final AccessGroup grupoAcesso) {
        grupoAcesso.getAccessGroupPermissions().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setAccessGroup(grupoAcesso)
        );
        return this.accessGroupService.save(grupoAcesso);
    }

    /**
     * @param id          long
     * @param grupoAcesso AccessGroup
     * @return AccessGroup
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + ACCESS_GROUP_PUT_ROLE + "')")
    public AccessGroup save(@PathVariable final long id, @RequestBody final AccessGroup grupoAcesso) {
        grupoAcesso.getAccessGroupPermissions().forEach(grupoAcessoPermissao ->
                grupoAcessoPermissao.setAccessGroup(grupoAcesso)
        );
        return this.accessGroupService.save(id, grupoAcesso);
    }

    /**
     * @param id long
     * @return Boolean
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('" + ACCESS_GROUP_DELETE_ROLE + "')")
    public Boolean delete(@PathVariable final long id) {
        this.accessGroupService.delete(id);
        return true;
    }

}
