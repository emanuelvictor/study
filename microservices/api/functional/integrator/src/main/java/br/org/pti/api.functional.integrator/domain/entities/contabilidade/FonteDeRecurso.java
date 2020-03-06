package br.org.pti.api.functional.integrator.domain.entities.contabilidade;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 26/12/2017
 */
@Entity
@Table(name = "cth010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FonteDeRecurso extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "cth_clvl", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "cth_desc01", nullable = false)
    private String descricao;

    /**
     * Construtor da classe
     */
    public FonteDeRecurso(){}
}
