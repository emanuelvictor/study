package br.org.pti.integrator.domain.entities.contabilidade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 07/03/2018
 */
public class LancamentoViagem {

    @Getter
    @Setter
    private String number;
    
    @Getter
    @Setter
    private BigDecimal balance;

}
