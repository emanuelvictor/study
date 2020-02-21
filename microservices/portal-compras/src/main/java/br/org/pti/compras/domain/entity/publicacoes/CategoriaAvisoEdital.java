package br.org.pti.compras.domain.entity.publicacoes;

import br.org.pti.compras.domain.entity.cadastros.Categoria;
import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.compras.Application.*;

@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(schema = CONFIGURACAO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"aviso_edital_id", "categoria_id"})
})
@AuditTable(value = "categoria_aviso_edital" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public class CategoriaAvisoEdital extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aviso_edital_id")
    private AvisoEdital avisoEdital;

}
