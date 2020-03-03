package br.org.pti.api.functional.inventario.domain.entity.pessoal.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
public class ColaboradorDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1951639564982345676L;

    /**
     *
     */
    private Long recno;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String matricula;

    /**
     *
     */
    private String nome;

    /**
     *
     */
    private String situacaoFolha;

    /**
     *
     */
    private CentroCustoDTO centroCustoDTO;

    /**
     *
     */
    private boolean cadastradoNoSistemaDeInventarios;

}

