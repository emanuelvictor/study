package br.org.pti.api.functional.integrator.infrastructure.utils.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception generica para utilizacao no integrador, dela todas as outras 
 * exceptions da aplicacao devem ser derivadas
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 31/10/2017
 */
public abstract class IntegradorException extends RuntimeException {

    @Getter
    @Setter
    private int httpStatus;
    @Getter
    @Setter
    private Object[] parameters;

    /**
     * 
     * @param message
     * @param parameters 
     */
    public IntegradorException(String message, Object... parameters) {
        this(message, 500, null, parameters);
    }

    /**
     * 
     * @param message
     * @param httpStatus
     * @param parameters 
     */
    public IntegradorException(String message, int httpStatus, Object... parameters) {
        this(message, httpStatus, null, parameters);
    }
    
    /**
     * 
     * @param message
     * @param throwable
     * @param httpStatus
     * @param parameters 
     */
    public IntegradorException(String message, int httpStatus, Throwable throwable, Object... parameters) {
        super(message, throwable);
        this.httpStatus = httpStatus;
        this.parameters = parameters;
    }

    /**
     * 
     * @return 
     */
    public String getCauseAsString() {
        return this.getCause() != null ? this.getCause().getMessage() : null;
    }    
}
