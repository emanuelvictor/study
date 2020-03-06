package br.org.pti.api.functional.integrator.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
public enum OpcionalNumerico {

    SIM("opcional-numerico.sim", "1"),
    NAO("opcional-numerico.nao", "2");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private OpcionalNumerico(String descricao, String valor) {
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
    public static OpcionalNumerico fromValor(String valor) {
        switch (valor) {
            case "1":
                return OpcionalNumerico.SIM;
            case "2":
                return OpcionalNumerico.NAO;
            default:
                throw new IllegalArgumentException(
                        "opcional-numerico.valor-nulo-informado");
        }
    }
}
