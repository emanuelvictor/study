package br.org.pti.integrador.domain.entities.ativofixo.converter;

import br.org.pti.integrador.domain.entities.ativofixo.TipoLocalizacao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do status do funcionario na folha de pagamento
 *
 * @author Arthur Gregorio
 * @version 1.0.0
 * @since 1.4.0, 21/08/2018
 */
@Converter(autoApply = true)
public class TipoLocalizacaoConverter implements AttributeConverter<TipoLocalizacao, Integer> {

    /**
     * Converte o valor da entity para a base de dados
     *
     * @param attribute TipoLocalizacao
     * @return Integer
     */
    @Override
    public Integer convertToDatabaseColumn(final TipoLocalizacao attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     *
     * @param dbData Integer
     * @return TipoLocalizacao
     */
    @Override
    public TipoLocalizacao convertToEntityAttribute(final Integer dbData) {
        return TipoLocalizacao.fromValor(dbData);
    }
}
