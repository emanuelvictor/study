package br.org.pti.compras.domain.entity.fornecedor;

import br.org.pti.compras.domain.entity.cadastros.Categoria;
import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.compras.Application.AUDIT_SUFFIX;
import static br.org.pti.compras.Application.FORNECEDOR;

@Data
@Entity
@Audited
@Table(schema = FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fornecedor_id", "categoria_id"})
})
@lombok.EqualsAndHashCode(callSuper = true)
@AuditTable(value = "categoria_fornecedor" + AUDIT_SUFFIX, schema = FORNECEDOR + AUDIT_SUFFIX)
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
