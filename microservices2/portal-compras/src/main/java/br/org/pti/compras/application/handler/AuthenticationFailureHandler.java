package br.org.pti.compras.application.handler;

import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    /**
     *
     */
    @Autowired
    IUsuarioRepository usuarioRepository;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException {

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            final String username = request.getParameter("email");
            final Usuario usuario = (Usuario) usuarioRepository.findByEmailIgnoreCaseOrDocumento(username);

            if (usuario != null) {
                usuario.registerFailedLogin();
                usuarioRepository.saveAndFlush(usuario);
            }


            response.setContentType("text/plain; charset=iso-8859-1");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nome de usuário ou senha não conferem");
            throw new BadCredentialsException("Nome de usuário ou senha não conferem");
        }

        if (exception instanceof DisabledException) {
            response.setContentType("text/plain; charset=iso-8859-1");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Este usuário está bloqueado");
            throw new LockedException(exception.getMessage());
        }

        if (exception instanceof LockedException) {
            response.setContentType("text/plain; charset=iso-8859-1");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Este usuário está bloqueado");
            throw new LockedException("Usuário bloqueado devido à muitas tentativas malsucedidas");
        }

        // lança excessao caso a senha esteja expirada
        if (exception instanceof CredentialsExpiredException) {
            response.setContentType("text/plain; charset=iso-8859-1");
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Senha de usuário está expirada");
            throw new CredentialsExpiredException("Nome de usuário ou senha não conferem");
        }

		/*if (exception instanceof InternalAuthenticationServiceException)
		{
			response.setContentType( "text/plain; charset=iso-8859-1" );
			response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Login não encontrado" );
			throw new InternalAuthenticationServiceException("Login não encontrado");
		}*/

        throw exception;
    }
}
