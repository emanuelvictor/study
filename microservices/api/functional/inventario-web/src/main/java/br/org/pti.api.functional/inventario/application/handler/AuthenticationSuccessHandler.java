package br.org.pti.api.functional.inventario.application.handler;

import br.org.pti.api.functional.inventario.application.context.ContextHolder;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import br.org.pti.api.functional.inventario.domain.repository.IUsuarioRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class AuthenticationSuccessHandler, callback para o sucesso durante a autenticação.
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(AuthenticationSuccessHandler.class.getName());

    /**
     *
     */
    @Autowired
    private IUsuarioRepository usuarioRepository;

    /**
     *
     */
    public AuthenticationSuccessHandler() {
        super();
        setRedirectStrategy((request, response, url) -> {
        });
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {

            final Usuario usuario = this.usuarioRepository.findById((ContextHolder.getAuthenticatedUser().getId())).orElse(null);
            Objects.requireNonNull(usuario).setUltimoAcesso(LocalDateTime.now());
            usuario.setTentativasLogin(null);
            usuario.setCodigo(null);
            this.usuarioRepository.save(usuario);

            usuario.setSenha(null);

            response.setCharacterEncoding("UTF8"); // this line solves the problem
            response.setContentType("application/json");
            usuario.setGrupoAcesso(null);
            usuario.setCentrosCusto(null);
            usuario.setExecutores(null);
            response.getWriter().write(new Gson().toJson(usuario));

            // Adiciona o usuário na resposta (response)
//             response.getWriter().write( new ObjectMapper().writeValueAsString(configuracao) );

        } catch (Exception e) {
            e.printStackTrace();
            LOG.severe("Ocorreu um problema ao atualizar o último login do usuário");
        }
    }
}
