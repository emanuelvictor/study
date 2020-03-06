package br.org.pti.api.functional.integrator.infrastructure.utils.jpa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * Representacao de uma entidade do protheus que somente realizaremos leituras, para que seja suportada a gravacao
 * utilize a classe {@link ProtheusPersistentEntity} e veja quando a criacao do recno
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 25/09/17
 */
@MappedSuperclass
public abstract class ReadOnlyProtheusPersistentEntity extends ProtheusPersistentEntity {

    @Id
    @Getter
    @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
    private Long recno;
}
