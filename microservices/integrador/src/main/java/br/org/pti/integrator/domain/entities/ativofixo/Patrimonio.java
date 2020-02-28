package br.org.pti.integrator.domain.entities.ativofixo;

import br.org.pti.integrator.domain.entities.rh.Funcionario;
import br.org.pti.integrator.domain.entities.ativofixo.converter.SimOuNaoConverter;
import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.infrastructure.utils.jpa.IPersistentEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 22/08/2018
 */
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "patrimonios")
@NamedEntityGraph(name = "Patrimonio.completo",
        attributeNodes = {
                @NamedAttributeNode(value = "centroCusto"),
                @NamedAttributeNode(value = "localizacao"),
                @NamedAttributeNode(value = "responsavel")
        })
public class Patrimonio implements IPersistentEntity<String>, Serializable {

    @Id
    @Getter
    @Column(name = "plaqueta", unique = true, updatable = false)
    private String plaqueta;

    @Getter
    @Setter
    @Column(name = "cbase")
    private String codigoBase;
    @Getter
    @Setter
    @Column(name = "item")
    private String item;
    @Getter
    @Setter
    @Column(name = "descricao")
    private String descricao;
    @Getter
    @Setter
    @Column(name = "departamento")
    private String departamento;
    @Getter
    @Setter
    @Column(name = "marca")
    private String marca;
    @Getter
    @Setter
    @Column(name = "modelo")
    private String modelo;
    @Getter
    @Setter
    @Column(name = "capacidade")
    private String capacidade;
    @Getter
    @Setter
    @Column(name = "complemento")
    private String complemento;
    @Getter
    @Setter
    @Column(name = "num_serie")
    private String numeroSerie;
    @Getter
    @Setter
    @Column(name = "doacao")
    @Convert(converter = SimOuNaoConverter.class)
    private boolean doacao;
    @Getter
    @Setter
    @Column(name = "importado")
    @Convert(converter = SimOuNaoConverter.class)
    private boolean importado;

    /**
     * Esta propriedade marca o codigo da RD0 do resposnavel, ou seja, o vinculo
     * real. O atributo Responsavel marca o vinculo RD0 + SRA, ou seja representa
     * a logica visual. No Protheus o codigo utiilizado para vinculo e o da RD0
     */
    @Getter
    @Setter
    @Column(name = "cod_resp")
    private String codigoResponsavel;
    @Getter
    @ManyToOne
    @JoinColumn(name = "matricula", referencedColumnName = "ra_mat")
    private Funcionario responsavel;
    @Getter
    @ManyToOne
    @JoinColumn(name = "centro_custo", referencedColumnName = "ctt_custo")
    private CentroCusto centroCusto;
    @Getter
    @ManyToOne
    @JoinColumn(name = "cod_local", referencedColumnName = "nl_codigo")
    private Localizacao localizacao;

    /**
     * {@inheritDoc }
     *
     * @return
     */
    @Override
    public boolean isSaved() {
        return true; // true because this entity is a mirror of a view
    }

    /**
     * {@inheritDoc }
     *
     * @return
     */
    @Override
    public boolean isNotSaved() {
        return !this.isSaved();
    }
}
