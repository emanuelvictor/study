package br.org.pti.integrador.domain.entities.compras.converters;

import br.org.pti.integrador.domain.entities.compras.OpcionalString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do tipo de pessoa
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 10/05/2019
 */
@Converter(autoApply = true)
public class OpcionalStringConverter implements AttributeConverter<OpcionalString, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(OpcionalString attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public OpcionalString convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : OpcionalString.fromValor(dbData);
    }
}
