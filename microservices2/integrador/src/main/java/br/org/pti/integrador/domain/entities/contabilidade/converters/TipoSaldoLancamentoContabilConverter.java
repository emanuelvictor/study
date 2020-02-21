package br.org.pti.integrador.domain.entities.contabilidade.converters;


import br.org.pti.integrador.domain.entities.contabilidade.TipoSaldoLancamentoContabil;

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
public class TipoSaldoLancamentoContabilConverter implements AttributeConverter<TipoSaldoLancamentoContabil, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(TipoSaldoLancamentoContabil attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoSaldoLancamentoContabil convertToEntityAttribute(String dbData) {
        return TipoSaldoLancamentoContabil.fromValor(dbData);
    }

}
