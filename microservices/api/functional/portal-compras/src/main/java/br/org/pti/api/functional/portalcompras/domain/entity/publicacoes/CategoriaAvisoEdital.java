package br.org.pti.api.functional.portalcompras.domain.entity.publicacoes;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Categoria;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(schema = ComprasApplication.CONFIGURACAO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"aviso_edital_id", "categoria_id"})
})
@AuditTable(value = "categoria_aviso_edital" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.PUBLICACAO + ComprasApplication.AUDIT_SUFFIX)
public class CategoriaAvisoEdital extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aviso_edital_id")
    private AvisoEdital avisoEdital;

}
