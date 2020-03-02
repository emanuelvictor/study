package br.org.pti.integrator.domain.entities.oracamento;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 14/01/2019
 */
public enum TipoExecucaoOrcamentaria {

    CREDITO("tipo-atividade.credito", 1),
    DEBITO("tipo-atividade.debito", 2);

    @Getter
    private final int valor;
    private final String descricao;

    /**
     *
     * @param descricao
     * @param valor
     */
    private TipoExecucaoOrcamentaria(String descricao, int valor) {
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
    public static TipoExecucaoOrcamentaria fromValor(int valor) {
        switch (valor) {
            case 1:
                return TipoExecucaoOrcamentaria.CREDITO;
            case 2:
                return TipoExecucaoOrcamentaria.DEBITO;
            default:
                throw new IllegalArgumentException(
                        "tipo-atividade.valor-nulo-informado");
        }
    }
}
