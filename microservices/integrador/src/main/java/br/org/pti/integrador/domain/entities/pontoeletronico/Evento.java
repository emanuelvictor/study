package br.org.pti.integrador.domain.entities.pontoeletronico;

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
 * @since 1.0.0, 28/07/2017
 */
@Entity
@Table(name = "sp9010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Evento extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "p9_codigo")
    private String codigo;
    @Getter
    @Setter
    @Column(name = "p9_desc")
    private String descricao;

    /**
     * 
     */
    public Evento() { }
}
