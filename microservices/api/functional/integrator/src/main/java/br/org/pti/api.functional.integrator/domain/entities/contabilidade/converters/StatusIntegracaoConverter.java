package br.org.pti.api.functional.integrator.domain.entities.contabilidade.converters;


import br.org.pti.api.functional.integrator.domain.entities.contabilidade.StatusIntegracao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do status da integracao para contabilidade e notas fiscais
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 07/08/2017
 */
@Converter(autoApply = true)
public class StatusIntegracaoConverter implements AttributeConverter<StatusIntegracao, Integer> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public Integer convertToDatabaseColumn(StatusIntegracao attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public StatusIntegracao convertToEntityAttribute(Integer dbData) {
        return StatusIntegracao.fromValor(dbData);
    }
}
