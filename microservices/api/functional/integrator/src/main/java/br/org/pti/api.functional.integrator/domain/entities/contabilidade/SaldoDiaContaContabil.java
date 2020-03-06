package br.org.pti.api.functional.integrator.domain.entities.contabilidade;

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
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 29/12/2017
 */
@Entity
@Table(name = "cq1010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SaldoDiaContaContabil extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @JoinColumn(name = "cq1_conta", referencedColumnName = "ct1_conta")
    @ManyToOne
    private ContaContabil contaContabil;
    @Getter
    @Setter
    @Column(name = "cq1_data")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataSaldo;
    @Getter
    @Setter
    @Column(name = "cq1_debito")
    private BigDecimal valorDebito;
    @Getter
    @Setter
    @Column(name = "cq1_credit")
    private BigDecimal valorCredito;
    @Getter
    @Setter
    @Column(name = "cq1_tpsald")
    private String tipoSaldo;
    @Getter
    @Setter
    @Column(name = "cq1_lp")
    private String lucroPerda;    

    /**
     * Construtor da classe SaldoDiaContaContabil
     */
    public SaldoDiaContaContabil() {}
    
}
