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
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/10/2017
 */
@Entity
@Table(name = "sb1010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Produto extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "b1_cod", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "b1_desc", nullable = false)
    private String descricao;
    @Getter
    @Setter
    @Column(name = "b1_grupo", nullable = false)
    private String grupo;
}
