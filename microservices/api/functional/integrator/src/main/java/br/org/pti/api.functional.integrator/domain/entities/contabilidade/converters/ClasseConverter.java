package br.org.pti.api.functional.integrator.domain.entities.contabilidade.converters;


import br.org.pti.api.functional.integrator.domain.entities.contabilidade.Classe;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 28/08/2018
 */
@Converter(autoApply = true)
public class ClasseConverter  implements AttributeConverter<Classe, Integer> {

    /**
     * Converte o valor da entity para a base de dados
     * 
     * @param attribute
     * @return 
     */
    @Override
    public Integer convertToDatabaseColumn(Classe attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    /**
     * Converte o valor da base para entidade
     * 
     * @param dbData
     * @return 
     */
    @Override
    public Classe convertToEntityAttribute(Integer dbData) {
        return Classe.fromValor(dbData);
    }
}
