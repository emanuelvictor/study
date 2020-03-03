package br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario;

public enum CentroCustoInventarioStatus {
    EM_CRIACAO, EM_EXECUCAO, RECUSADO, EM_ANALISE, APROVADO, FINALIZADO;

    public static final int EM_CRIACAO_VALUE = 0;
    public static final int EM_EXECUCAO_VALUE = 1;
    public static final int RECUSADO_VALUE = 2;
    public static final int EM_ANALISE_VALUE = 3;
    public static final int APROVADO_VALUE = 4;
    public static final int FINALIZADO_VALUE = 5;
}
