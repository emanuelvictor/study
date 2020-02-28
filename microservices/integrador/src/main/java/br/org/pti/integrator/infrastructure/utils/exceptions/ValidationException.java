package br.org.pti.integrator.infrastructure.utils.exceptions;

/**
 * Exception customizada para ser utilizada em erros de validacao de alguma 
 * regra de negocio do sistema
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 14/08/2017
 */
public class ValidationException extends IntegradorException {

    /**
     * {@inheritdoc}
     * 
     * @param message
     */
    public ValidationException(String message) {
        this(message, new Object(){});
    }
    
    /**
     * {@inheritdoc}
     * 
     * @param message
     * @param parameters 
     */
    public ValidationException(String message, Object... parameters) {
        super(message, 400, parameters);
    }
}
