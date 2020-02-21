package br.org.pti.integrador.domain.entities.financeiro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.org.pti.integrador.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

/**
 *
 * @author Willian Brecher
 *
 * @version 1.0.0
 * @since 1.0.0, 30/04/2019
 */
@Entity
@Table(name = "sx5010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*' "
        + " AND x5_tabela = 'X4' "
        + " AND length(trim(x5_chave)) = 3 ")
public class Banco extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Setter
    @Column(name = "x5_chave", nullable = false)
    private String codigo;
    @Getter
    @Setter
    @Column(name = "x5_descri", nullable = false)
    private String descricao;

}
