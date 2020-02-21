package br.org.pti.integrador.domain.entities.compras.converters;

import br.org.pti.integrador.domain.entities.compras.TipoPessoaJuridicaFornecedor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter do tipo de fornecedor da tabela SA2
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 09/05/2019
 */
@Converter(autoApply = true)
public class TipoPessoaJuridicaFornecedorConverter implements AttributeConverter<TipoPessoaJuridicaFornecedor, String> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public String convertToDatabaseColumn(TipoPessoaJuridicaFornecedor attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public TipoPessoaJuridicaFornecedor convertToEntityAttribute(String dbData) {
        return StringUtils.isBlank(dbData) ? null : TipoPessoaJuridicaFornecedor.fromValor(dbData);
    }
}
