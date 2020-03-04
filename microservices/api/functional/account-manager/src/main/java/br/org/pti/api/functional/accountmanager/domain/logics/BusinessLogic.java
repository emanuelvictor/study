package br.org.pti.api.functional.accountmanager.domain.logics;


import br.org.pti.api.functional.accountmanager.domain.entities.generic.PersistentEntity;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 2.0.0, 07/01/2020
 */
public interface BusinessLogic<T extends PersistentEntity> {

    /**
     * @param value
     */
    void perform(T value);
}
