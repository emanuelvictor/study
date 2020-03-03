package br.org.pti.integrator.domain.entities.oracamento;

import lombok.Getter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 23/01/2019
 */
public enum SituacaoContrato {

    CANCELADO("situacao-contrato.cancelado", "01"),
    EM_ELABORACAO("situacao-contrato.em-elaboracao", "02"),
    EMITIDO("situacao-contrato.emitido", "03"),
    EM_APROVACAO("situacao-contrato.em-aprovacao", "04"),
    VIGENTE("situacao-contrato.vigente", "05"),
    PARALISADO("situacao-contrato.paralisado", "06"),
    SOLICITADO_FINALIZACAO("situacao-contrato.solicitado-finalizacao", "07"),
    FINALIZADO("situacao-contrato.finalizado", "08"),
    REVISAO("situacao-contrato.revisao", "09"),
    REVISADO("situacao-contrato.revisado", "10");

    @Getter
    private final String valor;
    private final String descricao;

    /**
     *
     * @param descricao
     * @param valor
     */
    private SituacaoContrato(String descricao, String valor) {
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
    public static SituacaoContrato fromValor(String valor) {
        switch (valor) {
            case "01":
                return SituacaoContrato.CANCELADO;
            case "02":
                return SituacaoContrato.EM_ELABORACAO;
            case "03":
                return SituacaoContrato.EMITIDO;
            case "04":
                return SituacaoContrato.EM_APROVACAO;
            case "05":
                return SituacaoContrato.VIGENTE;
            case "06":
                return SituacaoContrato.PARALISADO;
            case "07":
                return SituacaoContrato.SOLICITADO_FINALIZACAO;
            case "08":
                return SituacaoContrato.FINALIZADO;
            case "09":
                return SituacaoContrato.REVISAO;
            case "10":
                return SituacaoContrato.REVISADO;
            default:
                throw new IllegalArgumentException(
                        "situacao-contrato.valor-nulo-informado");
        }
    }
}
