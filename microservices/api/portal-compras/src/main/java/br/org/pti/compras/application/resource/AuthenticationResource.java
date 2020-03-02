package br.org.pti.compras.application.resource;

import br.org.pti.compras.domain.entity.configuracao.Usuario;
import br.org.pti.compras.domain.service.AuthenticationService;
import br.org.pti.compras.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthenticationResource {

    /**
     *
     */
    private final AuthenticationService authenticationService;

    /**
     *
     */
    private final UsuarioService usuarioService;

    /**
     *
     */
    @GetMapping({"authenticated", "sistema/authenticated"})
    public Usuario getAuthenticated() {
        return this.authenticationService.getAuthenticatedUser();
    }

    /**
     *
     */
    @PostMapping({"/cadastrar-senha/{codigo}", "/sistema/cadastrar-senha/{codigo}"})
    public void resetSenha(@PathVariable String codigo, HttpServletRequest request) {
        final String senha = request.getParameter("senha");
        this.usuarioService.resetSenha(codigo, senha);
    }

    /**
     *
     */
    @GetMapping({"/recuperar-senha/{email:.+}", "/sistema/recuperar-senha/{email:.+}"})
    public void recoverPassword(@PathVariable("email") String email) {
        this.usuarioService.recoverSenha(email);
    }
}
