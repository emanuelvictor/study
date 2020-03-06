package br.org.pti.api.functional.integrator.domain.entities.compras;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
public enum TipoPessoaJuridicaFornecedor {

    MICRO_EMPRESA("tipo-pessoa-juridica.micro-empresa", "1"),
    EMPRESA_PEQUENO_PORTE("tipo-pessoa-juridica.empresa-pequeno-porte", "2"),
    MICROEMPREEDEDOR_INDIVIDUAL("tipo-pessoa-juridica.microempreendedor-individual", "3"),
    COOPERATIVA("tipo-pessoa-juridica.cooperativa", "4"),
    NAO_OPTANTE("tipo-pessoa-juridica.nao-optante", "5");

    @Getter
    private final String valor;

    private final String descricao;

    /**
     *
     * @param descricao
     */
    private TipoPessoaJuridicaFornecedor(String descricao, String valor) {
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
    public static TipoPessoaJuridicaFornecedor fromValor(String valor) {
        switch (valor) {
            case "1":
                return TipoPessoaJuridicaFornecedor.MICRO_EMPRESA;
            case "2":
                return TipoPessoaJuridicaFornecedor.EMPRESA_PEQUENO_PORTE;
            case "3":
                return TipoPessoaJuridicaFornecedor.MICROEMPREEDEDOR_INDIVIDUAL;
            case "4":
                return TipoPessoaJuridicaFornecedor.COOPERATIVA;
            case "5":
                return TipoPessoaJuridicaFornecedor.NAO_OPTANTE;
            default:
                throw new IllegalArgumentException(
                        "tipo-pessoa-juridica-fornecedor.valor-nulo-informado");
        }
    }
}
