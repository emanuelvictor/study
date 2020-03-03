package br.org.pti.integrator.domain.entities.oracamento.converter;


import br.org.pti.integrator.domain.entities.oracamento.TipoExecucaoOrcamentaria;

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
public class TipoExecucaoOrcamentariaConverter implements AttributeConverter<TipoExecucaoOrcamentaria, Integer> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public Integer convertToDatabaseColumn(TipoExecucaoOrcamentaria attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoExecucaoOrcamentaria convertToEntityAttribute(Integer dbData) {
        return TipoExecucaoOrcamentaria.fromValor(dbData);
    }
}
