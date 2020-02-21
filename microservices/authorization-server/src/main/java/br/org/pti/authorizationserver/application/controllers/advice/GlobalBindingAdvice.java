package br.org.pti.authorizationserver.application.controllers.advice;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 05/02/2020
 */
@ControllerAdvice
public class GlobalBindingAdvice {

    /**
     *
     * @param binder
     * @param request
     */
    @InitBinder
    public void register(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
