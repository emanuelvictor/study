package br.org.pti.compras.domain.entity.cadastros;

import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CADASTRO;

@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "tipo_cadastro_tipo_documento" + AUDIT_SUFFIX, schema = CADASTRO + AUDIT_SUFFIX)
@Table(schema = CADASTRO, uniqueConstraints = {
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
