package br.org.pti.api.functional.integrator.domain.entities.ativofixo.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 24/08/2018
 */
@Converter(autoApply = true)
public class SimOuNaoConverter implements AttributeConverter<Boolean, String> {

    /**
     * Converte o valor da entity para a base de dados
     *
     * @param attribute Boolean
     * @return String
     */
    @Override
    public String convertToDatabaseColumn(final Boolean attribute) {
        return attribute ? "S" : "N";
    }

    /**
     * Converte o valor da base para entidade
     *
     * @param dbData String
     * @return Boolean
     */
    @Override
    public Boolean convertToEntityAttribute(final String dbData) {
        return dbData.equals("S");
    }
}
