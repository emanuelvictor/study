package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 27/08/2018
 */
public enum Classe {

    ANALITICO("classe.analitico", 2),
    SINTETICO("classe.sintetico", 1);
    
    @Getter
    private final int valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private Classe(String descricao, int valor) {
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
    public static Classe fromValor(int valor) {
        switch (valor) {
            case 1:
                return Classe.SINTETICO;
            case 2:
                return Classe.ANALITICO;
            default:
                throw new IllegalArgumentException(
                        "classe.valor-nulo-informado");
        }
    }
}
