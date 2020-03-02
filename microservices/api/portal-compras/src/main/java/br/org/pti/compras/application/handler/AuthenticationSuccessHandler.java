package br.org.pti.compras.application.handler;

import br.org.pti.compras.application.context.ContextHolder;
import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.repository.IUsuarioRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

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
            usuario.setFornecedor(null);

            response.setCharacterEncoding("UTF8"); // this line solves the problem
            response.setContentType("application/json");
            usuario.setGrupoAcesso(null);
            response.getWriter().write(new Gson().toJson(usuario)); //TODO Não converte os getters

            // Adiciona o usuário na resposta (response)
//             response.getWriter().write( new ObjectMapper().writeValueAsString(configuracao) );

        } catch (Exception e) {
            e.printStackTrace();
            LOG.severe("Ocorreu um problema ao atualizar o último login do usuário");
        }
    }
}
