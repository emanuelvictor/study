package br.org.pti.integrador.domain.entities.oracamento;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 14/01/2019
 */
public enum TipoSaldoExecucaoOrcamentaria {
    ORCADO("tipo-saldo-atividade.orcado", "OR"),
    EMPENHADO("tipo-saldo-atividade.empenhado", "EM"),
    REALIZADO("tipo-saldo-atividade.realizado", "RE"),
    CONTRATO("tipo-saldo-atividade.contrato", "CT");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     * @param valor
     */
    private TipoSaldoExecucaoOrcamentaria(String descricao, String valor) {
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
    public static TipoSaldoExecucaoOrcamentaria fromValor(String valor) {
        switch (valor) {
            case "OR":
                return TipoSaldoExecucaoOrcamentaria.ORCADO;
            case "EM":
                return TipoSaldoExecucaoOrcamentaria.EMPENHADO;
            case "RE":
                return TipoSaldoExecucaoOrcamentaria.REALIZADO;
            case "CT":
                return TipoSaldoExecucaoOrcamentaria.CONTRATO;
            default:
                throw new IllegalArgumentException(
                        "tipo-saldo-atividade.valor-nulo-informado");
        }
    }
}
