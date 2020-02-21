package br.org.pti.integrador.domain.entities.rh;

import br.org.pti.integrador.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 20/08/2018
 */
@Entity
@NoArgsConstructor
@Table(name = "sqb010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*'")
@NamedEntityGraph(name = "Departamento.completo",
        attributeNodes = {
            @NamedAttributeNode(value = "gestor"),
            @NamedAttributeNode(value = "centroCusto")
        })
public class Departamento extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "qb_depto")
    private String codigo;
    @Getter
    @Setter
    @Column(name = "qb_descric")
    private String descricao;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "qb_cc", referencedColumnName = "ctt_custo")
    private CentroCusto centroCusto;
    @Getter
    @ManyToOne
    @JoinColumn(name = "qb_matresp", referencedColumnName = "ra_mat")
    private Gestor gestor;    
}
