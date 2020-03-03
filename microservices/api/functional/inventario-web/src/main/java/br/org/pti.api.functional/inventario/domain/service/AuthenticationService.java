package br.org.pti.inventario.domain.service;

import br.org.pti.inventario.application.context.ContextHolder;
import br.org.pti.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.inventario.domain.repository.IExecutorRepository;
import br.org.pti.inventario.domain.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;

    /**
     *
     */
    private final CentroCustoService centroCustoService;

    /**
     *
     */
    private final IExecutorRepository executorRepository;

    /**
     * @param username String
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final UserDetails usuario = this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(username);
        if (usuario == null)
            return null;

        return this.loadExecutoresAndCentrosCustoByUsuarioId((Usuario) usuario);
    }

    /**
     * Retorna o usuário autenticado
     *
     * @return Usuario
     */
    @Transactional(readOnly = true)
    public Usuario getAuthenticatedUser() {
        if (!ContextHolder.isAuthenticated())
            return null;

        final Usuario usuario = this.usuarioRepository.findById(ContextHolder.getAuthenticatedUser().getId()).orElse(null);
        if (usuario == null)
            return null;

        return this.loadExecutoresAndCentrosCustoByUsuarioId(usuario);
    }

    /**
     * @param usuario
     * @return
     */
    @Transactional(readOnly = true)
    Usuario loadExecutoresAndCentrosCustoByUsuarioId(final Usuario usuario) {
        if (usuario == null)
            return null;

        if (usuario.getExecutores() == null)
            usuario.setExecutores(new HashSet<>());
        if (usuario.getCentrosCusto() == null)
            usuario.setCentrosCusto(new HashSet<>());

        this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(usuario.getEmail());


        if (usuario.getId() != null) {
            usuario.setExecutores(this.executorRepository.findByUsuarioId(usuario.getId()));
            usuario.setCentrosCusto(this.centroCustoService.findByGestorId(usuario.getId()));
        } else {
            final Usuario usuarioByEmail = ((Usuario) this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(usuario.getEmail()));
            if (usuarioByEmail != null) {
                usuario.setExecutores(this.executorRepository.findByUsuarioId(usuarioByEmail.getId()));
                usuario.setCentrosCusto(this.centroCustoService.findByGestorId(usuarioByEmail.getId()));
            }
        }

        return usuario;
    }
}
