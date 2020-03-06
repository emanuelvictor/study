package br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.converters;


import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.StatusLancamento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 10/08/2017
 */
@Converter(autoApply = true)
public class StatusLancamentoConverter implements AttributeConverter<StatusLancamento, String> {

    /**
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(StatusLancamento attribute) {
        return attribute.getValor();
    }

    /**
     * 
     * @param dbData
     * @return 
     */
    @Override
    public StatusLancamento convertToEntityAttribute(String dbData) {
       return StatusLancamento.fromValor(dbData);
    }
}
