package br.org.pti.integrator.infrastructure.utils.jpa.converters;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converter customizado para dar suporte ao uso de LocalDate no JPA
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 07/08/2017
 */
@Converter(autoApply = true)
public class StringDateConverter implements AttributeConverter<LocalDate, String> {

    private static final String PATTERN = "yyyyMMdd";
    
    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(LocalDate attribute) {
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
    public LocalDate convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : LocalDate.parse(dbData,
                DateTimeFormatter.ofPattern(PATTERN));
    }
}
