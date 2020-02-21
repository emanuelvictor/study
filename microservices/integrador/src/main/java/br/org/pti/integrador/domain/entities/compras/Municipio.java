
package br.org.pti.integrador.domain.entities.compras;

import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
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
@Table(name = "cc2010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Municipio extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "cc2_codmun", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "cc2_mun", nullable = false)
    private String nome;
    @Getter
    @Setter
    @Column(name = "cc2_est", nullable = false)
    private String estado;
}
