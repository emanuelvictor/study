package br.org.pti.api.functional.integrator.domain.entities.pontoeletronico;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 07/02/2019
 */
public enum TipoFeriado {
    FIXO("tipo-feriado.fixo", "S"),
    MOVEL("tipo-feriado.movel", "N");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private TipoFeriado(String descricao, String valor) {
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
    public static TipoFeriado fromValor(String valor) {
        switch (valor) {
            case "S":
                return TipoFeriado.FIXO;
            case "N":
                return TipoFeriado.MOVEL;
            default:
                throw new IllegalArgumentException(
                        "tipo-feriado.valor-nulo-informado");
        }
    }
}

