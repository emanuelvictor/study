package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 13/03/2018
 */
public class SaldoPeriodoContaContabil {
    
    @Getter
    @Setter
    private ContaContabil contaContabil;    
    
    @Getter
    @Setter
    private BigDecimal valorDebito;
    @Getter
    @Setter
    private BigDecimal valorCredito;
}
