package br.org.pti.api.functional.portalcompras.domain.entity.endereco;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 *
 */
@Data
@Entity
@Audited
@NoArgsConstructor
@Table(schema = ComprasApplication.ENDERECO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "pais" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.ENDERECO + ComprasApplication.AUDIT_SUFFIX)
public class Pais extends AbstractEntity {

    private static final long serialVersionUID = -7513339061739700255L;

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

}
