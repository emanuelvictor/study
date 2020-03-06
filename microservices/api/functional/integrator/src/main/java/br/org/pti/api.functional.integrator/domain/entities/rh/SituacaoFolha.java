package br.org.pti.api.functional.integrator.domain.entities.rh;

import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
public enum SituacaoFolha {

    NORMAL("situacao-folha.normal", " "),
    FERIAS("situacao-folha.ferias", "F"),
    DEMITIDO("situacao-folha.demitido", "D"),
    AFASTADO("status-integracao.afastado", "A");
    
    @Getter
    private final String valor;

    private final String descricao;

    /**
     * 
     * @param valor
     * @param descricao 
     */
    private SituacaoFolha(String descricao, String valor) {
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
    public static SituacaoFolha fromValor(String valor) {
        switch (valor) {
            case "A":
                return SituacaoFolha.AFASTADO;
            case "D":
                return SituacaoFolha.DEMITIDO;
            case "F":
                return SituacaoFolha.FERIAS;
            case " ":
                return SituacaoFolha.NORMAL;
            default:
                throw new IllegalArgumentException(
                        "error.situacao-folha.valor-invalido");
        }
    }
}
