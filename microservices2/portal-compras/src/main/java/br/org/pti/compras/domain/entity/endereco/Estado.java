package br.org.pti.compras.domain.entity.endereco;


import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static br.org.pti.compras.Application.AUDIT_SUFFIX;
import static br.org.pti.compras.Application.ENDERECO;


/**
 *
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = ENDERECO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "pais_id"})
})
@AuditTable(value = "estado" + AUDIT_SUFFIX, schema = ENDERECO + AUDIT_SUFFIX)
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
