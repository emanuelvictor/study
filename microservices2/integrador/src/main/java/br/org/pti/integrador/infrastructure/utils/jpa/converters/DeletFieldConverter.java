package br.org.pti.integrador.infrastructure.utils.jpa.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 25/09/17
 */
@Converter(autoApply = false)
public class DeletFieldConverter implements AttributeConverter<Boolean, String> {

    /**
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "*" : " ";
    }

    /**
     *
     * @param dbValue
     * @return
     */
    @Override
    public Boolean convertToEntityAttribute(String dbValue) {
        return StringUtils.isNotBlank(dbValue);
    }
}

