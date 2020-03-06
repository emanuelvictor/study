package br.org.pti.api.functional.integrator.domain.entities.compras.converters;

import br.org.pti.api.functional.integrator.domain.entities.compras.TipoFornecedor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do tipo de fornecedor da tabela SA2
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
@Converter(autoApply = true)
public class TipoFornecedorConverter implements AttributeConverter<TipoFornecedor, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(TipoFornecedor attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoFornecedor convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : TipoFornecedor.fromValor(dbData);
    }
}
