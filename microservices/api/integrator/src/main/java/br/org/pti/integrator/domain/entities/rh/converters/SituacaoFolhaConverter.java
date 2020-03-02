package br.org.pti.integrator.domain.entities.rh.converters;

import br.org.pti.integrator.domain.entities.rh.SituacaoFolha;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do status do funcionario na folha de pagamento
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
@Converter(autoApply = true)
public class SituacaoFolhaConverter implements AttributeConverter<SituacaoFolha, String> {

    /**
     * Converte o valor da entity para a base de dados
     *
     * @param attribute
     * @return
     */
    @Override
    public String convertToDatabaseColumn(SituacaoFolha attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     *
     * @param dbData
     * @return
     */
    @Override
    public SituacaoFolha convertToEntityAttribute(String dbData) {
        return SituacaoFolha.fromValor(dbData);
    }
}
