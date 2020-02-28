package br.org.pti.integrator.domain.entities.oracamento;

import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.domain.entities.contabilidade.FonteDeRecurso;
import br.org.pti.integrator.domain.entities.contabilidade.NaturezaOrcamentaria;
import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 16/01/2019
 */
@Entity
@Table(name = "cnb010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemContrato extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "cnb_numero", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "cnb_descri", nullable = false)
    private String descricao;
    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    @JoinColumns({
        @JoinColumn(
                name = "cnb_contra",
                referencedColumnName = "cn9_numero"),
        @JoinColumn(
                name = "cnb_revisa",
                referencedColumnName = "cn9_revisa")
    })
    private Contrato contrato;
    @Getter
    @Setter
    @JoinColumn(name = "cnb_cc", referencedColumnName = "ctt_custo")
    @ManyToOne
    private CentroCusto centroCusto;
    @Getter
    @Setter
    @JoinColumn(name = "cnb_itemct", referencedColumnName = "ctd_item")
    @ManyToOne
    private NaturezaOrcamentaria naturezaOrcamentaria;
    @Getter
    @Setter
    @JoinColumn(name = "cnb_clvl", referencedColumnName = "cth_clvl")
    @ManyToOne
    private FonteDeRecurso fonteDeRecurso;
    @Getter
    @Setter
    @JoinColumn(name = "cnb_ec05db", referencedColumnName = "cv0_codigo")
    @ManyToOne
    private Atividade atividade;

}
