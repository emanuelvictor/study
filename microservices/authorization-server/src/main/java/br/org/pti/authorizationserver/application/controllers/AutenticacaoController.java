package br.org.pti.authorizationserver.application.controllers;

import br.org.pti.authorizationserver.application.components.Credential;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 24/01/2020
 */
@Controller
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    /**
     *
     * @param model
     * @return
     */
    @GetMapping
    public String form(Model model) {
        model.addAttribute("credential", new Credential());
        return "autenticacaoFormulario";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/erro")
    public String error(Model model) {
        model.addAttribute("autenticacaoErro", true);
        return this.form(model);
    }
}
