package br.org.pti.integrador.domain.entities.oracamento.converter;


import br.org.pti.integrador.domain.entities.oracamento.TipoSaldoExecucaoOrcamentaria;

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
public class TipoSaldoExecucaoOrcamentariaConverter implements AttributeConverter<TipoSaldoExecucaoOrcamentaria, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(TipoSaldoExecucaoOrcamentaria attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoSaldoExecucaoOrcamentaria convertToEntityAttribute(String dbData) {
        return TipoSaldoExecucaoOrcamentaria.fromValor(dbData);
    }

}
