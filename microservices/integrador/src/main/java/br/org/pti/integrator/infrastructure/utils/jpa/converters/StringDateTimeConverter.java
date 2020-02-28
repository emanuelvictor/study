package br.org.pti.integrator.infrastructure.utils.jpa.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Converter customizado para dar suporte ao uso de LocalDateTime no JPA
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 07/08/2017
 */
@Converter(autoApply = true)
public class StringDateTimeConverter implements AttributeConverter<LocalDateTime, String> {

    private static final String PATTERN = "yyyyMMdd HH:mm:ss";
    
    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(LocalDateTime attribute) {
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
    public LocalDateTime convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : LocalDateTime.parse(dbData,
                DateTimeFormatter.ofPattern(PATTERN));
    }
}
