package br.org.pti.api.functional.integrator.domain.entities.dto;

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
public class LancamentoViagemValorDTO {

    @Getter
    @Setter
    private BigDecimal value;
    @Getter
    @Setter
    private String type;

    /**
     * 
     * @param value
     * @param type 
     */
    public LancamentoViagemValorDTO(BigDecimal value, String type) {
        this.value = value;
        this.type = type;
    }
    
}
