package br.org.pti.integrator.domain.entities.contabilidade.converters;


import br.org.pti.integrator.domain.entities.contabilidade.FormaLancamentoContabil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 24/07/2018
 */
@Converter(autoApply = true)
public class FormaLancamentoContabilConverter  implements AttributeConverter<FormaLancamentoContabil, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(FormaLancamentoContabil attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public FormaLancamentoContabil convertToEntityAttribute(String dbData) {
        return FormaLancamentoContabil.fromValor(dbData);
    }

}
