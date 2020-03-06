package br.org.pti.api.functional.integrator.domain.entities.compras;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/10/2017
 */
@Entity
@Table(name = "sya010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Pais extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "ya_codgi", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "ya_sisexp", nullable = false)
    private String codigoBacen;
    @Getter
    @Setter
    @Column(name = "ya_descr", nullable = false)
    private String nome;
}
