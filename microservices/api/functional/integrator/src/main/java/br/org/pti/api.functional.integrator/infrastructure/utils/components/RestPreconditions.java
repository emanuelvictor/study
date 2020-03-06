package br.org.pti.api.functional.integrator.infrastructure.utils.components;

import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ResourceNotFoundException;
import br.org.pti.api.functional.integrator.infrastructure.utils.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Classe de utilidade para tornar o processo de checar algumas regras mais agil
 *
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 25/09/17
 */
public final class RestPreconditions {

    /**
     * Metodo utilizado para checar se uma determinada instancia foi ou nao localizada apos uma busca
     *
     * @param <T>    o tipo da nossa instancia
     * @param object a instancia que queremos saber se existe ou foi localizada
     * @return se ela foi localizada, devolvemos a instancia, se nao uma exception de {@link ResourceNotFoundException}
     */
    public static <T> T checkFound(final T object) {
        if (isNull(object)) {
            throw new ResourceNotFoundException("webservice.resource-not-found");
        }
        return object;
    }

    /**
     * @param <T>
     * @param object
     */
    public static <T> void validateFilter(final T object) {
        if (isNull(object)) {
            throw new ValidationException("webservice.resource-null-value");
        } else {
            if (object.getClass().isAssignableFrom(String.class)) {
                if (StringUtils.isBlank(String.valueOf(object))) {
                    throw new ValidationException("webservice.resource-null-value");
                }
            }
        }
    }

    /**
     * Metodo utilizado para checar se uma determinada instancia foi ou nao localizada apos uma busca
     *
     * @param <T>    o tipo da nossa instancia
     * @param object a instancia que queremos saber se existe ou foi localizada
     * @return se ela foi localizada, devolvemos a instancia, se nao uma exception de {@link ResourceNotFoundException}
     */
    public static <T> T checkFound(final Optional<T> object) {
        return object.orElseThrow(() ->
                new ResourceNotFoundException("webservice.resource-not-found"));
    }

    /**
     * Metodo utilizado para checar se uma determinada colecao foi ou nao localizada e esta preenchida apos uma busca
     *
     * @param collection a colecao que queremos testar
     * @param <T>        o tipo da nossa collection
     * @return se ela foi localizada e nao esta vazia, devolvemos a instancia da colecao, se nao uma exception de
     * {@link ResourceNotFoundException}
     */
    public static <T extends Collection> T checkFound(final T collection) {
        if (!Objects.isNull(collection)) {

            final List<?> value = (List<?>) collection;

            if (!value.isEmpty()) {
                return collection;
            }
        }
        throw new ResourceNotFoundException("webservice.resource-not-found");
    }

    /**
     * @param <T>
     * @param object
     * @return
     */
    private static <T> boolean isNull(final T object) {
        return Objects.isNull(object);
    }
}
