package br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.converters;


import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.TipoFeriado;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 07/02/2019
 */
@Converter(autoApply = true)
public class TipoFeriadoConverter implements AttributeConverter<TipoFeriado, String> {

    /**
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(TipoFeriado attribute) {
        return attribute.getValor();
    }

    /**
     *
     * @param dbData
     * @return
     */
    @Override
    public TipoFeriado convertToEntityAttribute(String dbData) {
        return TipoFeriado.fromValor(dbData);
    }
}
