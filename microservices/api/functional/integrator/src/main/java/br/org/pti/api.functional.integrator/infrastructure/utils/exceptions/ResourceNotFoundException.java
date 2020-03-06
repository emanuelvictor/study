package br.org.pti.api.functional.integrator.infrastructure.utils.exceptions;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 14/08/2017
 */
public class ResourceNotFoundException extends IntegradorException {

    /**
     * 
     * @param message 
     */
    public ResourceNotFoundException(String message) {
        this(message, new Object(){});
    }
    
    /**
     * 
     * @param message
     * @param parameters 
     */
    public ResourceNotFoundException(String message, Object... parameters) {
        super(message, 404, parameters);
    }
}
