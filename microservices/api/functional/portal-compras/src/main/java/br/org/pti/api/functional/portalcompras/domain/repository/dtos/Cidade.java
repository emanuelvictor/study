package br.org.pti.api.functional.portalcompras.domain.repository.dtos;

import lombok.Data;

@Data
public class Cidade {

    /**
     *
     */
    private String nome;

    /**
     *
     */
    private String estado;

    /**
     *
     */
    private String codigo;

    /**
     *
     */
    public Cidade() {
    }
}