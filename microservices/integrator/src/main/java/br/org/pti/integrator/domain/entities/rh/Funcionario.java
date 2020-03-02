package br.org.pti.integrator.domain.entities.rh;

import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.domain.entities.rh.converters.SituacaoFolhaConverter;
import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 25/07/2017
 */
@Entity
@NoArgsConstructor
@Table(name = "sra010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*'")
@NamedEntityGraph(name = "Funcionario.completo",
        attributeNodes = {
                @NamedAttributeNode(value = "centroCusto"),
                @NamedAttributeNode(value = "departamento", subgraph = "departamento")
        }, subgraphs = 
                @NamedSubgraph(name = "departamento", attributeNodes = {
                    @NamedAttributeNode("gestor"),
                    @NamedAttributeNode("centroCusto")
                }))
public class Funcionario extends ReadOnlyProtheusPersistentEntity {

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
    @Setter
    @Column(name = "ra_sitfolh")
    @Convert(converter = SituacaoFolhaConverter.class)
    private SituacaoFolha situacaoFolha;
    
    @Getter
    @ManyToOne
    @JoinColumn(name = "ra_depto", referencedColumnName = "qb_depto")
    private Departamento departamento;  
    @Getter
    @ManyToOne
    @JoinColumn(name = "ra_cc", referencedColumnName = "ctt_custo")
    private CentroCusto centroCusto;
}
