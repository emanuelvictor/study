package br.org.pti.compras.domain.entity.cadastros;

import br.org.pti.compras.domain.entity.generic.AbstractEntity;
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

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CADASTRO;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(schema = CADASTRO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "tipo_documento" + AUDIT_SUFFIX, schema = CADASTRO + AUDIT_SUFFIX)
public class TipoDocumento extends AbstractEntity {

    /**
     * Link para acesso externo do anexo.
     */
    @Length(max = 500)
    @Column(length = 500)
    protected String modelo;
    /**
     *
     */
    @NotBlank
    @Length(max = 250)
    @Column(nullable = false, length = 250)
    private String nome;
    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

}
