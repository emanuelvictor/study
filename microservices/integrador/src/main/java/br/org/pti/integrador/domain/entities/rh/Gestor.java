package br.org.pti.integrador.domain.entities.rh;

import br.org.pti.integrador.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
@Entity
@NoArgsConstructor
@Table(name = "sra010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*'")
@NamedEntityGraph(name = "Gestor.completo",
        attributeNodes = @NamedAttributeNode(value = "centroCusto"))
public class Gestor extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "ra_nome")
    private String nome;
    @Getter
    @Setter
    @Column(name = "ra_mat")
    private String matricula;
    @Getter
    @Setter
    @Column(name = "ra_email")
    private String email;

    @Getter
    @ManyToOne
    @JoinColumn(name = "ra_cc", referencedColumnName = "ctt_custo")
    private CentroCusto centroCusto;
}
