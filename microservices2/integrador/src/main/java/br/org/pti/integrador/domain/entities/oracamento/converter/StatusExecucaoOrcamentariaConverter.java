package br.org.pti.integrador.domain.entities.oracamento.converter;


import br.org.pti.integrador.domain.entities.oracamento.StatusExecucaoOrcamentaria;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 16/01/2019
 */
@Converter(autoApply = true)
public class StatusExecucaoOrcamentariaConverter implements AttributeConverter<StatusExecucaoOrcamentaria, Integer> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public Integer convertToDatabaseColumn(StatusExecucaoOrcamentaria attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public StatusExecucaoOrcamentaria convertToEntityAttribute(Integer dbData) {
        return StatusExecucaoOrcamentaria.fromValor(dbData);
    }
}
