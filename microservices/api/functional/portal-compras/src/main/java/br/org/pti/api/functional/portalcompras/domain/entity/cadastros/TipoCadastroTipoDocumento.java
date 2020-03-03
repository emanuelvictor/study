package br.org.pti.api.functional.portalcompras.domain.entity.cadastros;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "tipo_cadastro_tipo_documento" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CADASTRO + ComprasApplication.AUDIT_SUFFIX)
@Table(schema = ComprasApplication.CADASTRO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tipo_cadastro_id", "tipo_documento_id"})
})
public class TipoCadastroTipoDocumento extends AbstractEntity {

    /**
     *
     */
    @JoinColumn(name = "tipo_cadastro_id")
    @ManyToOne(optional = false)
    private TipoCadastro tipoCadastro;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private TipoDocumento tipoDocumento;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private boolean obrigatorio;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private boolean interno;

    /**
     * @param id long
     */
    public TipoCadastroTipoDocumento(final long id) {
        this.setId(id);
    }
}
