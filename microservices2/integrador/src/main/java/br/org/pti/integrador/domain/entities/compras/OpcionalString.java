package br.org.pti.integrador.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
public enum OpcionalString {

    SIM("opcional-string.sim", "S"),
    NAO("opcional-string.nao", "N");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private OpcionalString(String descricao, String valor) {
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
    public static OpcionalString fromValor(String valor) {
        switch (valor) {
            case "S":
                return OpcionalString.SIM;
            case "N":
                return OpcionalString.NAO;
            default:
                throw new IllegalArgumentException(
                        "opcional-string.valor-nulo-informado");
        }
    }
}
