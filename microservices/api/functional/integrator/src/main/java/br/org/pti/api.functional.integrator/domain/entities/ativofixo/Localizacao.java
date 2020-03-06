package br.org.pti.api.functional.integrator.domain.entities.ativofixo;


import br.org.pti.api.functional.integrator.domain.entities.ativofixo.converter.TipoLocalizacaoConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.4.0, 22/08/2018
 */
@Entity
@NoArgsConstructor
@Table(name = "snl010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*'")
public class Localizacao extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "nl_codigo")
    private String codigo;
    @Getter
    @Setter
    @Column(name = "nl_descric")
    private String descricao;

    @Getter
    @Setter
    @Column(name = "nl_tipoloc")
    @Convert(converter = TipoLocalizacaoConverter.class)
    private TipoLocalizacao tipoLocalizacao;
}
