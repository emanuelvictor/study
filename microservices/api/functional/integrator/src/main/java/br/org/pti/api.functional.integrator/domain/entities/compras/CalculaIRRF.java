package br.org.pti.integrator.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author willian.brecher
 * 
 * @version 1.0.0
 * @since 1.0.0, 04/09/2019
 */
public enum CalculaIRRF {
    NORMAL("calcula-irrf.normal", "1"),
    IRRF_BAIXA("calcula-irrf.irrf-baixa", "2"),
    SIMPLES("calcula-irrf.simples", "3"),
    EMPRESA_INDIVIDUAL("calcula-irrf.empresa-individual", "4");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private CalculaIRRF(String descricao, String valor) {
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
    public static CalculaIRRF fromValor(String valor) {
        switch (valor) {
            case "1":
                return CalculaIRRF.NORMAL;
            case "2":
                return CalculaIRRF.IRRF_BAIXA;
            case "3":
                return CalculaIRRF.SIMPLES;
            case "4":
                return CalculaIRRF.EMPRESA_INDIVIDUAL;
            default:
                throw new IllegalArgumentException(
                        "calcula-irrf.valor-nulo-informado");
        }
    }
}
