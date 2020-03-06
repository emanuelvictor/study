package br.org.pti.api.functional.integrator.infrastructure.utils.exceptions;

/**
 * Exception customizada utilizada para mostrar erros de execucao dentro de 
 * uma service, algo que nao e uma falha de validacao mas sim um ponto de 
 * execucao invalido dentro da aplicacao
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 14/08/2017
 */
public class ServiceException extends IntegradorException {

    /**
     * {@inheritdoc}
     * 
     * @param message 
     */
    public ServiceException(String message) {
        this(message, new Object(){});
    }
    
    /**
     * {@inheritdoc}
     * 
     * @param message
     * @param parameters 
     */
    public ServiceException(String message, Object... parameters) {
        super(message, 404, parameters);
    }
}
