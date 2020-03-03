package br.org.pti.api.functional.inventario.infrastructure.feign;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@JsonDeserialize(as = SimplePageImpl.class)
public interface PageMixIn {
}
