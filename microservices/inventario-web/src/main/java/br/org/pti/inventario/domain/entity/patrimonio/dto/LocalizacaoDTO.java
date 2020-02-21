package br.org.pti.inventario.domain.entity.patrimonio.dto;

import br.org.pti.inventario.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LocalizacaoDTO extends AbstractEntity {

    private String recno;
    private String codigo;
    private String descricao;
    private String tipoLocalizacao;

}

