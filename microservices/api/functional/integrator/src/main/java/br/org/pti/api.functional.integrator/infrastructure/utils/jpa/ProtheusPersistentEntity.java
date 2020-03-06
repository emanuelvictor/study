package br.org.pti.api.functional.integrator.infrastructure.utils.jpa;


import java.io.Serializable;
import javax.persistence.*;

import br.org.pti.api.functional.integrator.infrastructure.utils.jpa.converters.DeletFieldConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Setter;

/**
 * Classe que representa uma entidade do ERP protheus
 *
 * Para utilizacao do mapeamento via ORM das entidades do ERP e necessaria a implementacao desta classe ou de sua
 * filha {@link ReadOnlyProtheusPersistentEntity} onde a definicao de qual usar passa pelo fato de a entidade ter ou
 * nao suporte para gravacao no BD.
 *
 * Caso a entidade seja somente leitura, utilizar a abstracao acima.
 *
 * Caso ela precise de leitura e escrita, deve-se implementar partindo desta classe e em consequencia disso criar o
 * RECNO manualmente referenciando adequadamente o gerador da sequence da tabela (deve haver a sequence criada no BD)
 *
 * Exemplo:
 *
 * @Id
 * @Getter
 * @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZI1_SEQ")
 * @SequenceGenerator(name = "ZI1_SEQ", sequenceName = "ZI1010_SEQ", allocationSize = 1)
 * private Long recno;
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 1.0.0, 28/07/2017
 */
@MappedSuperclass
public abstract class ProtheusPersistentEntity implements IPersistentEntity<Long>, Serializable {

    @Setter
    @Column(name = "d_e_l_e_t_", nullable = false)
    @Convert(converter = DeletFieldConverter.class)
    private boolean delet;

    /**
     * Getter para o ID da entidade persistente
     *
     * @return o id da entidade
     */
    public abstract Long getRecno();

    /**
     * @return se o item estao ou nao deletado
     */
    @JsonIgnore
    public boolean isDelet() {
        return this.delet;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isSaved() {
        return this.getRecno() != null && this.getRecno() != 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isNotSaved() {
        return !this.isSaved();
    }
}
