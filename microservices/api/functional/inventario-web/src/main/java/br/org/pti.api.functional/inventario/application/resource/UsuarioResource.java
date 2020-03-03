package br.org.pti.api.functional.inventario.application.resource;

import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.Executor;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.api.functional.inventario.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static br.org.pti.api.functional.inventario.application.resource.Roles.*;

/**
 * RESTFul de usuários
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestController
@RequiredArgsConstructor
@RequestMapping({USUARIO_MAPPING_RESOURCE, "/sistema/" + USUARIO_MAPPING_RESOURCE})
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
    @PreAuthorize("hasAnyAuthority('" + USUARIO_GET_ROLE + "', '" + ROOT + "')")
    public Page<Usuario> listByFilters(final String defaultFilter, final Boolean ativoFilter, final Pageable pageable) {
        return this.usuarioService.listByFilters(defaultFilter, ativoFilter, pageable);
    }

    /**
     * @param id long
     * @return Usuario
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + USUARIO_GET_ROLE + "', '" + ROOT + "')")
    public Usuario findById(@PathVariable final long id) {
        return this.usuarioService.findById(id);
    }

    /**
     * @param usuario Usuario
     * @return Usuario
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('" + USUARIO_POST_ROLE + "', '" + ROOT + "')")
    public Usuario save(@RequestBody final Usuario usuario) {
        return this.usuarioService.save(usuario);
    }

    /**
     * @param id      long
     * @param usuario Usuario
     * @return Usuario
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + USUARIO_PUT_ROLE + "', '" + ROOT + "')")
    public Usuario updateUsuario(@PathVariable final long id, @RequestBody final Usuario usuario) {
        return this.usuarioService.save(id, usuario);
    }

    /**
     * @param id {long}
     * @return boolean
     */
    @PutMapping("/ativo")
    @PreAuthorize("hasAnyAuthority('" + USUARIO_PUT_ACTIVATE_ROLE + "', '" + ROOT + "')")
    public boolean updateAtivo(@RequestBody final long id) {
        return this.usuarioService.updateAtivo(id).getAtivo();
    }

    /**
     * @param id Long
     */
    @PutMapping("/alterar-senha/{id}")
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
    @PreAuthorize("hasAnyAuthority('" + USUARIO_GET_ROLE + "', '" + ROOT + "')")
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
     * @param newPassword String
     * @return Usuario
     */
    @GetMapping("{usuarioId}/change-password")
    @PreAuthorize("hasAnyAuthority('" + USUARIO_PUT_CHANGE_PASSWORD_ROLE + "', '" + ROOT + "')")
    Usuario changePassword(@PathVariable final long usuarioId, @RequestParam final String newPassword) {
        return this.usuarioService.changePassword(usuarioId, newPassword);
    }

    /**
     * @param usuarioId long
     * @return Set<Executor>
     */
    @GetMapping("{usuarioId}/executores")
    public Set<Executor> getExecutoresByUsuarioId(@PathVariable final long usuarioId) {
        final Set<Executor> executores = this.usuarioService.findById(usuarioId).getExecutores();

        executores.forEach(executor -> {
            executor.getCentroCustoInventario().setExecutores(null);
            executor.getUsuario().setExecutores(null);
            executor.getCentroCustoInventario().getInventario().setCentrosCusto(null);
        });
        
        return executores;
    }

    /**
     * @param usuarioId long
     * @return Set<Executor>
     */
    @GetMapping("{usuarioId}/centros-custo")
    public Set<CentroCusto> getCentrosCustoByUsuarioId(@PathVariable final long usuarioId) {
        final Set<CentroCusto> centrosCusto = this.usuarioService.findById(usuarioId).getCentrosCusto();

        centrosCusto.forEach(executor -> {
            executor.getGestor().setExecutores(null);
            executor.getGestor().setCentrosCusto(null);
            executor.getGestor().setGrupoAcesso(null);
        });

        return centrosCusto;
    }
}
