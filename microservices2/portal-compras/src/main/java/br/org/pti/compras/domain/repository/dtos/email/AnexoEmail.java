package br.org.pti.compras.domain.repository.dtos.email;

import lombok.Data;

@Data
public class AnexoEmail {


    /**
     *
     */
    private Email email;

    /**
     *
     */
    private String caminho;

    /**
     *
     */
    private String nome;

    /**
     *
     */
    private String type;

    /**
     * Armazena o conteúdo em si
     */
    private byte[] conteudo;


}
