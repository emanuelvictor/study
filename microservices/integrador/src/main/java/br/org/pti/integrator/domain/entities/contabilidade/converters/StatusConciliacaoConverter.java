package br.org.pti.integrator.domain.entities.contabilidade.converters;


import br.org.pti.integrator.domain.entities.contabilidade.StatusConciliacao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Willian Brecher
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
@Converter(autoApply = true)
public class StatusConciliacaoConverter implements AttributeConverter<StatusConciliacao, String> {

    /**
     * Converte o valor da entity para a base de dados
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(StatusConciliacao attribute) {
        return attribute == null ? " " : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     *
     * @param dbData
     * @return
     */
    @Override
    public StatusConciliacao convertToEntityAttribute(String dbData) {
        return dbData == null ?
                StatusConciliacao.fromValor(" ") :
                StatusConciliacao.fromValor(dbData);
    }

}
