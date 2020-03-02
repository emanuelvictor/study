package br.org.pti.integrator.domain.entities.compras;

import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author willian.brecher
 * @version 1.0.0
 * @since 1.0.0, 04/09/2019
 */
@Entity
@Table(name = "bancos")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ViewBancos extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Getter
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
