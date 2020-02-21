package br.org.pti.integrador.domain.entities.contabilidade;

import br.org.pti.integrador.domain.entities.contabilidade.converters.FormaLancamentoContabilConverter;
import br.org.pti.integrador.domain.entities.contabilidade.converters.StatusConciliacaoConverter;
import br.org.pti.integrador.domain.entities.contabilidade.converters.TipoLancamentoContabilConverter;
import br.org.pti.integrador.domain.entities.contabilidade.converters.TipoSaldoLancamentoContabilConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusPersistentEntity;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.BlobStringConverter;
import br.org.pti.integrador.infrastructure.utils.jpa.converters.StringDateConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@Entity
@Table(name = "ct2010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LancamentoContabil extends ProtheusPersistentEntity {

    @Id
    @Getter
    @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CT2_SEQ")
    @SequenceGenerator(name = "CT2_SEQ", sequenceName = "CT2010_SEQ", allocationSize = 1)
    private Long recno;
    @Getter
    @Column(name = "ct2_data", insertable = false, updatable = false)
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataLancamento;
    @Getter
    @Column(name = "ct2_lote", insertable = false, updatable = false)
    private String numeroLote;
    @Getter
    @Column(name = "ct2_sblote", insertable = false, updatable = false)
    private String numeroSubLote;
    @Getter
    @Column(name = "ct2_doc", insertable = false, updatable = false)
    private String numeroDocumento;
    @Getter
    @Column(name = "ct2_dc", insertable = false, updatable = false)
    @Convert(converter = TipoLancamentoContabilConverter.class)
    private TipoLancamentoContabil tipoLancamento;
    @Getter
    @JoinColumn(name = "ct2_credit", referencedColumnName = "ct1_conta",
            insertable = false, updatable = false)
    @ManyToOne
    private ContaContabil contaCredito;
    @Getter
    @JoinColumn(name = "ct2_debito", referencedColumnName = "ct1_conta",
            insertable = false, updatable = false)
    @ManyToOne
    private ContaContabil contaDebito;
    @Getter
    @Column(name = "ct2_hist", insertable = false, updatable = false)
    private String historico;
    @Getter
    @JoinColumn(name = "ct2_ccd", referencedColumnName = "ctt_custo",
            insertable = false, updatable = false)
    @ManyToOne
    private CentroCusto centroCustoDebito;
    @Getter
    @JoinColumn(name = "ct2_ccc", referencedColumnName = "ctt_custo",
            insertable = false, updatable = false)
    @ManyToOne
    private CentroCusto centroCustoCredito;
    @Getter
    @JoinColumn(name = "ct2_itemd", referencedColumnName = "ctd_item",
            insertable = false, updatable = false)
    @ManyToOne
    private NaturezaOrcamentaria naturezaOrcamentariaDebito;
    @Getter
    @JoinColumn(name = "ct2_itemc", referencedColumnName = "ctd_item",
            insertable = false, updatable = false)
    @ManyToOne
    private NaturezaOrcamentaria naturezaOrcamentariaCredito;
    @Getter
    @JoinColumn(name = "ct2_clvldb", referencedColumnName = "cth_clvl",
            insertable = false, updatable = false)
    @ManyToOne
    private FonteDeRecurso fonteDeRecursoDebito;
    @Getter
    @JoinColumn(name = "ct2_clvlcr", referencedColumnName = "cth_clvl",
            insertable = false, updatable = false)
    @ManyToOne
    private FonteDeRecurso fonteDeRecursoCredito;
    @Getter
    @Column(name = "ct2_tpsald", insertable = false, updatable = false)
    @Convert(converter = TipoSaldoLancamentoContabilConverter.class)
    private TipoSaldoLancamentoContabil tipoSaldo;
    @Getter
    @Column(name = "ct2_origem", insertable = false, updatable = false)
    private String origem;
    @Getter
    @Column(name = "ct2_valor", insertable = false, updatable = false)
    private BigDecimal valor;
    @Getter
    @Column(name = "ct2_linha", insertable = false, updatable = false)
    private String linha;
    @Getter
    @Column(name = "ct2_manual", insertable = false, updatable = false)
    @Convert(converter = FormaLancamentoContabilConverter.class)
    private FormaLancamentoContabil formaLancamento;
    @Getter
    @Setter
    @Column(name = "ct2_x_ccr")
    @Convert(converter = StatusConciliacaoConverter.class)
    private StatusConciliacao conciliadoCredito;
    @Getter
    @Setter
    @Column(name = "ct2_x_cdb")
    @Convert(converter = StatusConciliacaoConverter.class)
    private StatusConciliacao conciliadoDebito;
    @Getter
    @Setter
    @Column(name = "ct2_x_mcdb")
    @Convert(converter = BlobStringConverter.class)
    private String obsConciliadoDebito;
    @Getter
    @Setter
    @Column(name = "ct2_x_mccr")
    @Convert(converter = BlobStringConverter.class)
    private String obsConciliadoCredito;
    @Getter
    @Setter
    @Column(name = "ct2_x_dtcd")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataConciliadoDebito;
    @Getter
    @Setter
    @Column(name = "ct2_x_dtcc")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataConciliadoCredito;

    /**
     * Construtor da classe
     */
    public LancamentoContabil() {
    }

}
