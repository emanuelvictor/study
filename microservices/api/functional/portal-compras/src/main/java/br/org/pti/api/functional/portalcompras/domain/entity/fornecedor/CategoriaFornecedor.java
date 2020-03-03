package br.org.pti.api.functional.portalcompras.domain.entity.fornecedor;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Categoria;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@Table(schema = ComprasApplication.FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fornecedor_id", "categoria_id"})
})
@lombok.EqualsAndHashCode(callSuper = true)
@AuditTable(value = "categoria_fornecedor" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
public class CategoriaFornecedor extends AbstractEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Categoria categoria;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

}
