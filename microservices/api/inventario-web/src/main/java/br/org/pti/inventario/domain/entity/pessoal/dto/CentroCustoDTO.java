package br.org.pti.inventario.domain.entity.pessoal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@EqualsAndHashCode
public class CentroCustoDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1951634183012345676L;

    /**
     *
     */
    private String emailGestor;

    /**
     *
     */
    private String nomeGestor;

    /**
     *
     */
    @NotBlank
    private String codigo;

    /**
     *
     */
    @NotBlank
    @Size(max = 500)
    private String descricao;

    /**
     *
     */
    public CentroCustoDTO() {
    }

    /**
     * @param descricaoCentroCusto String
     */
    public void setDescricaoCentroCusto(final String descricaoCentroCusto) {
        this.descricao = descricaoCentroCusto;
    }

    /**
     * @param centroCusto String
     */
    public void setCentroCusto(final String centroCusto) {
        this.codigo = centroCusto;
    }

    /**
     * @return ColaboradorDTO
     */
    public ColaboradorDTO getGestor() {
        final ColaboradorDTO gestor = new ColaboradorDTO();
        gestor.setEmail(this.emailGestor);
        gestor.setNome(this.nomeGestor);
        return gestor;
    }

}
