package br.org.pti.integrador.domain.entities.pontoeletronico;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 28/07/2017
 */
public enum StatusLancamento {

    BAIXADO("status-lancamento.baixado", "B"),
    PENDENTE("status-lancamento.pendente", "");
    
    @Getter
    private final String valor;
    
    private final String descricao;

    /**
     * 
     * @param descricao 
     */
    private StatusLancamento(String descricao, String valor) {
        this.valor = valor;
        this.descricao = descricao;
    }

    /**
     * @return 
     */
    @Override
    public String toString() {
        return this.descricao;
    }

    /**
     * 
     * @param valor
     * @return 
     */
    public static StatusLancamento fromValor(String valor) {
        return StringUtils.isNotBlank(valor) ? StatusLancamento.BAIXADO : StatusLancamento.PENDENTE;
    }
}
