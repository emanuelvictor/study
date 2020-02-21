package br.org.pti.authorizationserver.application.controllers.advice;

import br.org.pti.authorizationserver.domain.exceptions.BusinessLogicException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 04/02/2020
 */
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    /**
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = {
            IllegalStateException.class,
            BusinessLogicException.class,
            IllegalArgumentException.class
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
