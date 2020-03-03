package br.org.pti.api.functional.portalcompras.domain.service;

import br.org.pti.api.functional.portalcompras.domain.repository.IUsuarioRepository;
import br.org.pti.api.functional.portalcompras.application.context.ContextHolder;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    /**
     *
     */
    private final IUsuarioRepository usuarioRepository;

    /**
     *
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmailIgnoreCaseOrDocumento(username);
    }

    /**
     * Retorna o usuário autenticado
     */
    public Usuario getAuthenticatedUser() {
        if (!ContextHolder.isAuthenticated())
            return null;
        return this.usuarioRepository.findById(ContextHolder.getAuthenticatedUser().getId()).orElse(null);
    }
}
