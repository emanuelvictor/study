package br.org.pti.integrator.domain.entities.contabilidade;

import br.org.pti.integrator.infrastructure.utils.jpa.ReadOnlyProtheusPersistentEntity;
import br.org.pti.integrator.infrastructure.utils.jpa.converters.StringDateConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.3.0, 25/09/2019
 */
@Entity
@NoArgsConstructor
@Table(name = "ctt010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Where(clause = "d_e_l_e_t_ <> '*' AND ctt_bloq = '2'")
public class CentroCusto extends ReadOnlyProtheusPersistentEntity {

    @Getter
    @Column(name = "ctt_custo")
    private String codigo;
    @Getter
    @Column(name = "ctt_desc01")
    private String descricao;
    @Getter
    @Column(name = "ctt_ccsup")
    private String superior;
    @Getter
    @Column(name = "ctt_classe")
    private Classe classe;
    @Getter
    @Column(name = "ctt_dtexsf")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataFim;
    @Getter
    @Column(name = "ctt_dtexis")
    @Convert(converter = StringDateConverter.class)
    private LocalDate dataInicio;
}
