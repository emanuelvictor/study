package br.org.pti.integrator.infrastructure.utils.jpa.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 28/12/2017
 */
@Converter(autoApply = false)
public class BlobStringConverter implements AttributeConverter<String, byte[]> {

    /**
     * 
     * @param attribute
     * @return 
     */
    @Override
    public byte[] convertToDatabaseColumn(String attribute) {
        return StringUtils.isBlank(attribute) ? new byte[]{} : attribute.getBytes();
    }

    /**
     * 
     * @param dbData
     * @return 
     */
    @Override
    public String convertToEntityAttribute(byte[] dbData) {
        return dbData == null ? new String() : new String(dbData);
    }
    
}
