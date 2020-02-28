package br.org.pti.integrator.domain.entities.oracamento;

import br.org.pti.integrator.domain.entities.oracamento.converter.StatusExecucaoOrcamentariaConverter;
import br.org.pti.integrator.domain.entities.oracamento.converter.TipoExecucaoOrcamentariaConverter;
import br.org.pti.integrator.domain.entities.oracamento.converter.TipoSaldoExecucaoOrcamentariaConverter;
import br.org.pti.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrator.domain.entities.contabilidade.FonteDeRecurso;
import br.org.pti.integrator.domain.entities.contabilidade.NaturezaOrcamentaria;
import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 18/01/2019
 */
@Entity
@Table(name = "akd010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExecucaoOrcamentaria extends ReadOnlyProtheusPersistentEntity {

    
    @Getter
    @Setter
    @Column(name = "akd_valor1", nullable = false)
    private BigDecimal valor;
    @Getter
    @Setter
    @Column(name = "akd_tipo", nullable = false)
    @Convert(converter = TipoExecucaoOrcamentariaConverter.class)
    private TipoExecucaoOrcamentaria tipoExecucaoOrcamentaria;
    @Getter
    @Setter
    @Column(name = "akd_status", nullable = false)
    @Convert(converter = StatusExecucaoOrcamentariaConverter.class)
    private StatusExecucaoOrcamentaria statusExecucaoOrcamentaria;
    @Getter
    @Setter
    @Column(name = "akd_tpsald", nullable = false)
    @Convert(converter = TipoSaldoExecucaoOrcamentariaConverter.class)
    private TipoSaldoExecucaoOrcamentaria tipoSaldoExecucaoOrcamentaria;    
    @Getter
    @Setter
    @JoinColumn(name = "akd_cc", referencedColumnName = "ctt_custo")
    @ManyToOne
    private CentroCusto centroCusto;
    @Getter
    @Setter
    @JoinColumn(name = "akd_itctb", referencedColumnName = "ctd_item")
    @ManyToOne
    private NaturezaOrcamentaria naturezaOrcamentaria;
    @Getter
    @Setter
    @JoinColumn(name = "akd_clvlr", referencedColumnName = "cth_clvl")
    @ManyToOne
    private FonteDeRecurso fonteDeRecurso;
    @Getter
    @Setter
    @JoinColumn(name = "akd_ent05", referencedColumnName = "cv0_codigo")
    @ManyToOne
    private Atividade atividade;

}
