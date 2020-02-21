package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
public enum StatusConciliacao {

    NOVO("status-conciliacao.novo", " "),
    EM_PROGRESSO("status-conciliacao.em-progresso", "A"),
    CONCILIADO("status-conciliacao.conciliado", "C"),
    PENDENTE("status-conciliacao.pendente", "P");
    
    @Getter
    private final String valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private StatusConciliacao(String descricao, String valor) {
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
    public static StatusConciliacao fromValor(String valor) {
        switch (valor) {
            case "A":
                return StatusConciliacao.EM_PROGRESSO;
            case "P":
                return StatusConciliacao.PENDENTE;
            case "C":
                return StatusConciliacao.CONCILIADO;
            default:
                return StatusConciliacao.NOVO;                
        }
    }
}
