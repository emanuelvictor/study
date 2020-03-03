package br.org.pti.integrator.domain.entities.contabilidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 12/03/2018
 */
public class LancamentoItemConciliacao {

    @Getter
    @Setter
    private LocalDate date;
    
    @Getter
    @Setter
    private String historic;
    
    @Getter
    @Setter
    private BigDecimal creditValue;
    @Getter
    @Setter
    private BigDecimal debitValue;
}
