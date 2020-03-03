package br.org.pti.api.functional.portalcompras.infrastructure.spring;

import br.org.pti.api.functional.portalcompras.domain.repository.IUsuarioRepository;
import br.org.pti.api.functional.portalcompras.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.portalcompras.domain.repository.IUsuarioRepository;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 01/10/2019
 */
@Order(0)
@Component
public class CreateAdminTask implements InitialTask {

    private PasswordEncoder passwordEncoder;
    private IUsuarioRepository usuarioRepository;

    /**
     *
     * @param passwordEncoder
     * @param usuarioRepository
     */
    public CreateAdminTask(PasswordEncoder passwordEncoder, IUsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void perform() {
        this.usuarioRepository.findByEmail("admin@pti.org.br")
                .ifPresentOrElse(saved -> {/* empty */}, () -> {

                    final Usuario usuario = new Usuario();

                    usuario.setNome("Administrador");
                    usuario.setAdministrador(true);
                    usuario.setAtivo(true);
                    usuario.setEmail("admin@pti.org.br");
                    usuario.setSenha(this.passwordEncoder.encode("admin"));

                    this.usuarioRepository.save(usuario);
                });
    }
}
