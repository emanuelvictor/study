package br.org.pti.integrador.domain.entities.compras.converters;

import br.org.pti.integrador.domain.entities.compras.CalculaIRRF;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 04/09/2019
 */
@Converter(autoApply = true)
public class CalculaIRRFConverter implements AttributeConverter<CalculaIRRF, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(CalculaIRRF attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public CalculaIRRF convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : CalculaIRRF.fromValor(dbData);
    }
}
