package br.org.pti.authorizationserver.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 03/02/2020
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    /**
     * @return String
     */
    @GetMapping
    public String toDashboard() {
        return "dashboard";
    }
}
