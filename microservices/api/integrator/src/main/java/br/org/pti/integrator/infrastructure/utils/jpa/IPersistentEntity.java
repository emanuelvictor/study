package br.org.pti.integrator.infrastructure.utils.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * O contrato de uma entidade persistente do sistema, todas as classes que
 * tem persistencia devem implementar esta interface
 *
 * @param <ID> qualquer tipo serializavel
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.0.0, 28/07/2017
 */
public interface IPersistentEntity<ID extends Serializable> {

    /**
     * Metodo que indica se uma entidade ja foi ou nao persistida (salva)
     *
     * @return true se a entidade ja persistida, false se nao
     */
    @JsonIgnore
    boolean isSaved();

    /**
     * Metodo de conveniencia para saber se esta entidade foi salva o nao, basi-
     * camente utilizado quando estamos trabalhando com streams e precisamos
     * utilizar method references
     *
     * @return true para entidades nao persistidas, false para persistidas
     */
    @JsonIgnore
    boolean isNotSaved();
}
