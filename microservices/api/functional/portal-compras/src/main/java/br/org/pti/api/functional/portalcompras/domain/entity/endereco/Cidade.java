package br.org.pti.api.functional.portalcompras.domain.entity.endereco;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 *
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = ComprasApplication.ENDERECO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "estado_id"})
})
@AuditTable(value = "cidade" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.ENDERECO + ComprasApplication.AUDIT_SUFFIX)
public class Cidade extends AbstractEntity {

    /**
     *
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

    /**
     *
     */
    @NotNull
    @ManyToOne(optional = false)
    private Estado estado;

    /**
     *
     */
    @Transient
    private String codigo;

    /**
     * @param nome   {String}
     * @param estado {Estado}
     */
    public Cidade(final @NotEmpty @Length(max = 200) String nome, final Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    /**
     * @param id {Long}
     */
    public Cidade(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public Cidade() {
    }
}
