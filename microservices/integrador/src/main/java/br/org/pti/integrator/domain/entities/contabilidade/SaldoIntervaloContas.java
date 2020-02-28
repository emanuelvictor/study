package br.org.pti.integrator.domain.entities.contabilidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 15/03/2018
 */
public class SaldoIntervaloContas {

    @Getter
    @Setter
    private String conta;
    @Getter
    @Setter
    private String descricao;
    @Getter
    @Setter
    private BigDecimal creditoPeriodo;
    @Getter
    @Setter
    private BigDecimal debitoPeriodo;
    @Getter
    @Setter
    private BigDecimal creditoAnterior;
    @Getter
    @Setter
    private BigDecimal debitoAnterior;
}
