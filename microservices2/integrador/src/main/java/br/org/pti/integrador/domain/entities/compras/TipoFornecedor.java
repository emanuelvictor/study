package br.org.pti.integrador.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
public enum TipoFornecedor {

    COMERCIO_INDUSTRIA("tipo-fornecedor.comercio-industria", "CI"),
    PESSOA_FISICA("tipo-fornecedor.pessoa-fisica", "PF"),
    PRESTACAO_SERVICO("tipo-fornecedor.prestacao-servico", "OS");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private TipoFornecedor(String descricao, String valor) {
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
    public static TipoFornecedor fromValor(String valor) {
        switch (valor) {
            case "CI":
                return TipoFornecedor.COMERCIO_INDUSTRIA;
            case "PF":
                return TipoFornecedor.PESSOA_FISICA;
            case "OS":
                return TipoFornecedor.PRESTACAO_SERVICO;
            default:
                throw new IllegalArgumentException(
                        "tipo-fornecedor.valor-nulo-informado");
        }
    }
}
