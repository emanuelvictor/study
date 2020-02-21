package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
public enum FormaLancamentoContabil {

    MANUAL("forma-lancamento-contabil.manual", "1"),
    AUTOMATICO("forma-lancamento-contabil.automatico", "2");
    
    @Getter
    private final String valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private FormaLancamentoContabil(String descricao, String valor) {
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
    public static FormaLancamentoContabil fromValor(String valor) {
        switch (valor) {
            case "1":
                return FormaLancamentoContabil.MANUAL;
            case "2":
                return FormaLancamentoContabil.AUTOMATICO;       
            default:
                throw new IllegalArgumentException(
                        "forma-lancamento-contabil.valor-nulo-informado");
        }
    }    
    
}
