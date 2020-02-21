package br.org.pti.integrador.domain.entities.compras.converters;

import br.org.pti.integrador.domain.entities.compras.TipoPessoa;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do tipo de pessoa
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 07/08/2017
 */
@Converter(autoApply = true)
public class TipoPessoaConverter implements AttributeConverter<TipoPessoa, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(TipoPessoa attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoPessoa convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : TipoPessoa.fromValor(dbData);
    }
}
