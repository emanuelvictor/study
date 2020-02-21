package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
public enum TipoSaldoLancamentoContabil {
    
    REAL("tipo-saldo-lancamento-contabil.real", "1"),
    PREVISTO("tipo-saldo-lancamento-contabil.previsto", "2"),
    GERENCIAL("tipo-saldo-lancamento-contabil.gerencial", "3"),
    EMPENHADO("tipo-saldo-lancamento-contabil.empenhado", "4"),
    PRE_LANCAMENTO("tipo-saldo-lancamento-contabil.pre-lancamento", "5");
    
    @Getter
    private final String valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private TipoSaldoLancamentoContabil(String descricao, String valor) {
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
    public static TipoSaldoLancamentoContabil fromValor(String valor) {
        switch (valor) {
            case "1":
                return TipoSaldoLancamentoContabil.REAL;
            case "2":
                return TipoSaldoLancamentoContabil.PREVISTO;
            case "3":
                return TipoSaldoLancamentoContabil.GERENCIAL;
            case "4":
                return TipoSaldoLancamentoContabil.EMPENHADO;
            case "5":
                return TipoSaldoLancamentoContabil.PRE_LANCAMENTO;                
            default:
                return TipoSaldoLancamentoContabil.REAL;                
        }
    }
}
