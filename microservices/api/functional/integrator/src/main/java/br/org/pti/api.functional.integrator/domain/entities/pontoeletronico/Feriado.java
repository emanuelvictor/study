package br.org.pti.api.functional.integrator.domain.entities.pontoeletronico;

import br.org.pti.api.functional.integrator.domain.entities.pontoeletronico.converters.TipoFeriadoConverter;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.converters.StringDateConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 07/02/2019
 */
@Entity
@Table(name = "sp3010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Feriado extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "p3_desc")
    private String descricao;
    @Getter
    @Setter
    @Column(name = "p3_mesdia")
    private String mesDia;
    @Getter
    @Setter
    @Column(name = "p3_data")
    @Convert(converter = StringDateConverter.class)
    private LocalDate data;
    @Getter
    @Setter
    @Column(name = "p3_fixo")
    @Convert(converter = TipoFeriadoConverter.class)
    private TipoFeriado tipoFeriado;
    
}

