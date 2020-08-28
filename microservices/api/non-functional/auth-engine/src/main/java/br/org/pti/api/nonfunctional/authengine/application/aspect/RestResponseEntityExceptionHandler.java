package br.org.pti.api.nonfunctional.authengine.application.aspect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.logging.Logger;

/**
 * Class RestResponseEntityExceptionHandler, serve para capturar os erros da aplicação e trata-los
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

    /**
     *
     */
    private final MessageSource messageSource;

    /**
     * @param messageSource {MessageSource}
     */
    @Autowired
    public RestResponseEntityExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Trata exceções de Constraint geradas pelo PostgreSQL
     *
     * @param exception {DataIntegrityViolationException}
     * @param request   {WebRequest}
     * @return ResponseEntity<Object>
     */
    @ExceptionHandler(InvalidGrantException.class)
    public ResponseEntity<Object> handleException(final InvalidGrantException exception, final WebRequest request) {
        return handleExceptionInternal(exception, new Error(exception.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    /**
     * @param ex      Exception
     * @param body    Error
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return ResponseEntity<Object>
     */
    private ResponseEntity<Object> handleExceptionInternal(final Exception ex, final @Nullable Error body,
                                                           final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        LOGGER.warning("Aspecto capturado: " + ex.getLocalizedMessage());

        return super.handleExceptionInternal(ex, body, headers, status, request);

    }

    /**
     * Converte de camel case para legível/literal
     */
    private String camelCaseToHumanReadable(String phrase) {
        final String[] words = StringUtils.splitByCharacterTypeCamelCase(phrase);

        return (words.length == 1) ? phrase : StringUtils.capitalize(StringUtils.join(words, " "));
    }

    /**
     * Converte de snake case para legível/literal
     */
    private String snakeCaseToHumanReadable(String phrase) {
        final String[] words = StringUtils.splitByWholeSeparator(phrase, "_");

        return (words.length == 1) ? phrase : StringUtils.capitalize(StringUtils.join(words, " "));
    }

    /**
     * Entidade auxiliar utilizada para serializar os erros para o front-end
     */
    public static class Error {

        /**
         *
         */
        private String message;

        /**
         *
         */
        public Error() {
        }

        /**
         * @param message String
         */
        public Error(final String message) {
            this.message = message;
        }

        /**
         * @return String
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message String
         */
        public void setMessage(final String message) {
            this.message = message;
        }
    }
}
