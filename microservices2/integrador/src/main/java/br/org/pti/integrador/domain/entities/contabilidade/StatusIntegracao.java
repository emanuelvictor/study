package br.org.pti.integrador.domain.entities.contabilidade;

import lombok.Getter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 11/10/2017
 */
public enum StatusIntegracao {

    INTEGRADO("status-integracao.integrado", 1),
    NAO_INTEGRADO("status-integracao.nao-integrado", 0);
    
    @Getter
    private final int valor;

    private final String descricao;

    /**
     * 
     * @param descricao
     * @param valor 
     */
    private StatusIntegracao(String descricao, int valor) {
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
    public static StatusIntegracao fromValor(int valor) {
        switch (valor) {
            case 1:
                return StatusIntegracao.INTEGRADO;
            case 0:
                return StatusIntegracao.NAO_INTEGRADO;
            default:
                throw new IllegalArgumentException(
                        "status-integracao.valor-nulo-informado");
        }
    }
}
