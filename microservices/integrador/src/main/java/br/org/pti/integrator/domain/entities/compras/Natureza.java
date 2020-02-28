package br.org.pti.integrator.domain.entities.compras;

import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
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
 * @since 1.0.0, 04/09/2019
 */
@Entity
@Table(name = "sed010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Natureza extends ReadOnlyProtheusPersistentEntity {
    
//    TODO: Refatorar utilizacao de dependencias. Esta gerando erro de dependency cycle
    
    @Getter
    @Setter
    @Column(name = "ed_codigo", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "ed_descric", nullable = false)
    private String descricao;    

}
