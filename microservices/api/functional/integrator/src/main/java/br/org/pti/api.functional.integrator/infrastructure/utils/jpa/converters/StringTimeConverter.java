package br.org.pti.integrator.infrastructure.utils.jpa.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 07/11/2017
 */
@Converter(autoApply = true)
public class StringTimeConverter implements AttributeConverter<LocalTime, String> {

    private static final String PATTERN = "HH:mm:ss";

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(LocalTime attribute) {
        return attribute == null ? " " : DateTimeFormatter.
                ofPattern(PATTERN).format(attribute);
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public LocalTime convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : LocalTime.parse(dbData,
                DateTimeFormatter.ofPattern(PATTERN));
    }
}
