package br.org.pti.integrador.domain.entities.oracamento;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 21/01/2019
 */
public enum StatusExecucaoOrcamentaria {
    
    APROVADO("status-execucao-orcamentaria.aprovado", 1),
    INVALIDO("status-execucao-orcamentaria.invalido", 2),
    ESTORNADO("status-execucao-orcamentaria.estornado", 3);

    @Getter
    private final int valor;
    private final String descricao;

    /**
     *
     * @param descricao
     * @param valor
     */
    private StatusExecucaoOrcamentaria(String descricao, int valor) {
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
    public static StatusExecucaoOrcamentaria fromValor(int valor) {
        switch (valor) {
            case 1:
                return StatusExecucaoOrcamentaria.APROVADO;
            case 2:
                return StatusExecucaoOrcamentaria.INVALIDO;
            case 3:
                return StatusExecucaoOrcamentaria.ESTORNADO;
            default:
                throw new IllegalArgumentException(
                        "status-execucao-orcamentaria.valor-nulo-informado");
        }
    }
}
