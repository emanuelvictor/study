package br.org.pti.integrador.domain.entities.dto;

import br.org.pti.integrador.domain.entities.contabilidade.CentroCusto;
import br.org.pti.integrador.domain.entities.contabilidade.FonteDeRecurso;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 18/01/2019
 */
public class DadosOrcamentariosDTO {

    @Getter
    @Setter
    private BigDecimal valorEmpenhado;
    @Getter
    @Setter
    private BigDecimal valorContrato;
    @Getter
    @Setter
    private BigDecimal ValorRealizado;
    @Getter
    @Setter
    private BigDecimal valorOrcado;
    @Getter
    @Setter
    private BigDecimal valorSaldo;
    @Getter
    @Setter
    private CentroCusto centroCusto;
    @Getter
    @Setter
    private FonteDeRecurso fonteDeRecurso;
    
    /**
     * Construtor
     */
    public DadosOrcamentariosDTO(){
        this.valorEmpenhado = BigDecimal.ZERO;
        this.valorContrato = BigDecimal.ZERO;
        this.ValorRealizado = BigDecimal.ZERO;
        this.valorOrcado = BigDecimal.ZERO;
        this.valorSaldo = BigDecimal.ZERO;
    }
    
}
