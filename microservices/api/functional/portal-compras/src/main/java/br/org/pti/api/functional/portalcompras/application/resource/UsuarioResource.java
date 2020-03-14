package br.org.pti.api.functional.portalcompras.application.resource;

import br.org.pti.api.functional.portalcompras.domain.service.UsuarioService;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping({Roles.USUARIO_MAPPING_RESOURCE, "/sistema/" + Roles.USUARIO_MAPPING_RESOURCE})
public class UsuarioResource {

    /**
     *
     */
    private final UsuarioService usuarioService;

    /**
     * @param defaultFilter String
     * @param ativoFilter   Boolean
     * @param pageable      Pageable
     * @return Page<Usuario>
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_GET_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Page<Usuario> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
        return this.usuarioService.listByFilters(defaultFilter, ativoFilter, pageable);
    }

    /**
     * @param id long
     * @return Usuario
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_GET_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Usuario findById(@PathVariable final long id) {
        return this.usuarioService.findById(id);
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Usuario save(@RequestBody final Usuario usuario) {
        return this.usuarioService.save(usuario);
    }

    /**
     * @param id      long
     * @param usuario Usuario
     * @return Usuario
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_PUT_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Usuario updateUsuario(@PathVariable final long id, @RequestBody final Usuario usuario) {
        return this.usuarioService.save(id, usuario);
    }

    /**
     * @param id {long}
     * @return boolean
     */
    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_PUT_ACTIVATE_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        return this.usuarioService.updateAtivo(id).getAtivo();
    }

    /**
     * @param id Long
     */
    @PutMapping("/alterar-senha/{id}")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_PUT_CHANGE_PASSWORD_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public void updatePassword(@PathVariable final long id, HttpServletRequest request) {
        final String currentPassword = request.getParameter("senhaAtual");
        final String newPassword = request.getParameter("novaSenha");

        this.usuarioService.updatePassword(id, currentPassword, newPassword);
    }

    /**
     * @param username username
     * @return Usuario
     */
    @GetMapping("ldap")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_GET_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    public Usuario findByLdapUsername(@RequestParam(value = "username", required = false) final String username) {
        return this.usuarioService.findByLdapUsername(username);
    }

    /**
     * @param id {Long}
     * @return Set<GrantedAuthority>
     */
    @GetMapping("{id}/authorities")
    public Set<GrantedAuthority> getAuthoritiesByUsuarioId(@PathVariable final long id) {
        return this.usuarioService.getAuthoritiesByUsuarioId(id);
    }

    /**
     * @param usuarioId   long
     * @param password    String
     * @param newPassword String
     * @return Usuario
     */
    @GetMapping("{usuarioId}/change-password")
    @PreAuthorize("hasAnyAuthority('" + Roles.USUARIO_POST_ROLE + "', '" + Roles.ADMINISTRADOR + "')")
    Usuario changePassword(@PathVariable final long usuarioId, @RequestParam(required = false) final String password, @RequestParam final String newPassword) {
        return this.usuarioService.changePassword(usuarioId, password, newPassword);
    }

}