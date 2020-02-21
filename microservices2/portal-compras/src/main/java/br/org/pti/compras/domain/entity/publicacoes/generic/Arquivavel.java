package br.org.pti.compras.domain.entity.publicacoes.generic;

public interface Arquivavel {

    void setNome(final String nome);

    void setCaminho(final String caminho);

    void setLink(final String link);

    void setExterno(final boolean externo);

    void setType(final String type);

    void setConteudo(final byte[] conteudo);

}
