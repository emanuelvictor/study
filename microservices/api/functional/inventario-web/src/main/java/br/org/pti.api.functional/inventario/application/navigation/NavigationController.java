package br.org.pti.api.functional.inventario.application.navigation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Controller
public class NavigationController {

    /**
     * @return ModelAndView
     */
    @GetMapping("/")
    public ModelAndView web() {
        return new ModelAndView("modules/index");
    }

    /**
     * @return ModelAndView
     */
    @GetMapping({"/sistema"})
    public ModelAndView mobile() {
        return new ModelAndView("modules/sistema/index");
    }
}
