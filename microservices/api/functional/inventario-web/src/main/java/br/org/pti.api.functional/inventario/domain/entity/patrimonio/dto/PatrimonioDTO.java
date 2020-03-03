package br.org.pti.api.functional.inventario.domain.entity.patrimonio.dto;

import br.org.pti.api.functional.inventario.domain.entity.patrimonio.Localizacao;
import br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.dto.CentroCustoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@NoArgsConstructor
public class PatrimonioDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1951852364192345676L;

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private String codigoBase;

    /**
     *
     */
    private String item;

    /**
     *
     */
    private String plaqueta;

    /**
     *
     */
    private String capacidade;

    /**
     *
     */
    private String complemento;

    /**
     *
     */
    private String departamento;

    /**
     *
     */
    private String descricao;

    /**
     *
     */
    private boolean doacao;

    /**
     *
     */
    private boolean importado;

    /**
     *
     */
    private String modelo;

    /**
     *
     */
    private String marca;

    /**
     *
     */
    private String numeroSerie;

    /**
     *
     */
    private String observacao;

    /**
     *
     */
    private CentroCustoDTO centroCusto;

    /**
     *
     */
    private Localizacao localizacao;

    /**
     *
     */
    private Localizacao localizacaoAnterior;

    /**
     *
     */
    private Boolean encontrado;

    /**
     *
     */
    private CentroCusto centroCustoAnterior;

    /**
     *
     */
    private CentroCustoInventario centroCustoInventario;

    /**
     * @param localizacao
     */
    public PatrimonioDTO(final Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * @param codigoBase
     * @param item
     * @param plaqueta
     * @param localizacao
     * @param centroCustoInventario
     */
    public PatrimonioDTO(final String codigoBase, final String item, final String plaqueta,
                         final Localizacao localizacao, final CentroCustoInventario centroCustoInventario) {
        this.codigoBase = codigoBase;
        this.item = item;
        this.plaqueta = plaqueta;
        this.localizacao = localizacao;
        this.centroCustoInventario = centroCustoInventario;
    }
}

