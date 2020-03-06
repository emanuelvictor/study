package br.org.pti.api.functional.integrator.domain.entities.pontoeletronico;

import br.org.pti.api.functional.integrator.domain.entities.contabilidade.CentroCusto;
import br.org.pti.api.functional.integrator.domain.entities.rh.Funcionario;
import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.converters.StatusLancamentoConverter;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.converters.StringDateConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2017
 */
@Entity
@Table(name = "spi010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BancoHora extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "pi_data")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataLancamento;
    @Getter
    @Setter
    @Column(name = "pi_quant")
    private BigDecimal quantidade;
    @Getter
    @Setter
    @Column(name = "pi_quantv")
    private BigDecimal quantidadeValorizada;
    @Getter
    @Setter
    @Column(name = "pi_dtbaix")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataBaixa;

    @Getter
    @Setter
    @Column(name = "pi_status")
    @Convert(converter = StatusLancamentoConverter.class)
    private StatusLancamento statusLancamento;

    @Getter
    @OneToOne
    @JoinColumn(name = "pi_pd", referencedColumnName = "p9_codigo")
    private Evento evento;
    @Getter
    @ManyToOne
    @JoinColumn(name = "pi_mat", referencedColumnName = "ra_mat")
    private Funcionario funcionario;
    @Getter
    @ManyToOne
    @JoinColumn(name = "pi_cc", referencedColumnName = "ctt_custo")
    private CentroCusto centroCusto;
    
    /**
     *
     */
    public BancoHora() { }
}
