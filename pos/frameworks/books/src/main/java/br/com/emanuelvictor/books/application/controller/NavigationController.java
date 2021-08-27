package br.com.emanuelvictor.books.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
