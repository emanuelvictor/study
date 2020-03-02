package br.org.pti.integrator.domain.entities.ativofixo;

import lombok.Getter;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 23/08/2018
 */
public enum TipoLocalizacao {

    FISICO("tipo-localizacao.fisico", 1),
    CONTROLE("tipo-localizacao.controle", 2);

    @Getter
    private final int valor;

    private final String descricao;

    /**
     * @param valor
     * @param descricao
     */
    private TipoLocalizacao(final String descricao, final int valor) {
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
     * @param valor
     * @return
     */
    public static TipoLocalizacao fromValor(int valor) {
        switch (valor) {
            case 1:
                return TipoLocalizacao.FISICO;
            case 2:
                return TipoLocalizacao.CONTROLE;
            default:
                throw new IllegalArgumentException(
                        "error.tipo-localizacao.valor-invalido");
        }
    }
}
