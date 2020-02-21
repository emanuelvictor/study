package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
public enum TipoLancamentoContabil {

    DEBITO("tipo-lancamento-contabil.debito", "1"),
    CREDITO("tipo-lancamento-contabil.credito", "2"),
    PARTIDA_DOBRADA("tipo-lancamento-contabil.partida-dobrada", "3");
    
    @Getter
    private final String valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private TipoLancamentoContabil(String descricao, String valor) {
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
    public static TipoLancamentoContabil fromValor(String valor) {
        switch (valor) {
            case "1":
                return TipoLancamentoContabil.DEBITO;
            case "2":
                return TipoLancamentoContabil.CREDITO;
            case "3":
                return TipoLancamentoContabil.PARTIDA_DOBRADA;                
            default:
                throw new IllegalArgumentException(
                        "tipo-lancamento-contabil.valor-nulo-informado");
        }
    }
}
