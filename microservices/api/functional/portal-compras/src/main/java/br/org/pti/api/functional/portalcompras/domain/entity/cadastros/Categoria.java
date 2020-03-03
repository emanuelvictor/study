package br.org.pti.api.functional.portalcompras.domain.entity.cadastros;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Audited
@AuditTable(value = "categoria" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CADASTRO + ComprasApplication.AUDIT_SUFFIX)
@NoArgsConstructor
@Table(schema = ComprasApplication.CADASTRO)
@EqualsAndHashCode(callSuper = true)
public class Categoria extends AbstractEntity {

    /**
     *
     */
    @NotBlank
    @Length(max = 50)
    @Column(nullable = false, length = 50)
    private String nome;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

}
