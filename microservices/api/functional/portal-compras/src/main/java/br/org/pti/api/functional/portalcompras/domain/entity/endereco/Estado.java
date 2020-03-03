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
        @UniqueConstraint(columnNames = {"nome", "pais_id"})
})
@AuditTable(value = "estado" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.ENDERECO + ComprasApplication.AUDIT_SUFFIX)
public class Estado extends AbstractEntity {

    private static final long serialVersionUID = 8414044637595122330L;

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

    @NotEmpty
    @Length(max = 2)
    @Column(nullable = false, length = 2)
    private String uf;

    @NotNull
    @ManyToOne(optional = false)
    private Pais pais;

}
