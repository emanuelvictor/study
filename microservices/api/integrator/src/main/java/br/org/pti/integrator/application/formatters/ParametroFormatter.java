package br.org.pti.integrator.application.formatters;

/**
 *
 * @param <T>
 * 
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 23/08/2017
 */
public interface ParametroFormatter<T> {

    /**
     * 
     * @param conteudo
     * @return 
     */
    T format(String conteudo);
}
