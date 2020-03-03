package br.org.pti.integrator.infrastructure.utils.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 19/03/2018
 */
@Converter(autoApply = false)
public class BlockedClientConverter implements AttributeConverter<Boolean, String> {

    /**
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "1" : "2";
    }

    /**
     *
     * @param dbValue
     * @return
     */
    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return dbValue.equals("1");
    }
}
