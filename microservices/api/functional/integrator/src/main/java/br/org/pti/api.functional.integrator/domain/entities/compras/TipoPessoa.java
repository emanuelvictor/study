package br.org.pti.api.functional.integrator.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 02/10/2017
 */
public enum TipoPessoa {

    FISICA("tipo-pessoa.fisica", "F"),
    JURIDICA("tipo-pessoa.juridica", "J"),
    EXTRANGEIRO("tipo-pessoa.juridica", "X");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private TipoPessoa(String descricao, String valor) {
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
    public static TipoPessoa fromValor(String valor) {
        switch (valor) {
            case "F":
                return TipoPessoa.FISICA;
            case "J":
                return TipoPessoa.JURIDICA;
            case "X":
                return TipoPessoa.EXTRANGEIRO;
            default:
                throw new IllegalArgumentException(
                        "tipo-pessoa.valor-nulo-informado");
        }
    }
}
