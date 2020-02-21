package br.org.pti.integrador.domain.entities.oracamento;

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
 * @since 1.0.0, 14/01/2019
 */
@Entity
@Table(name = "cv0010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Atividade extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "cv0_codigo", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "cv0_desc", nullable = false)
    private String descricao;  

}
