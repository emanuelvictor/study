package br.org.pti.api.functional.integrator.domain.entities.oracamento.converter;

import br.org.pti.api.functional.integrator.domain.entities.oracamento.SituacaoContrato;

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
public class SituacaoContatoConverter implements AttributeConverter<SituacaoContrato, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(SituacaoContrato attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public SituacaoContrato convertToEntityAttribute(String dbData) {
        return SituacaoContrato.fromValor(dbData);
    }
}
