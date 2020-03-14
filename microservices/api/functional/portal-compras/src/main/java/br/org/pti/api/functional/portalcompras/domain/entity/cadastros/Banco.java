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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Audited
@AuditTable(value = "banco" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CADASTRO + ComprasApplication.AUDIT_SUFFIX)
@NoArgsConstructor
@Table(schema = ComprasApplication.CADASTRO)
@EqualsAndHashCode(callSuper = true)
public class Banco extends AbstractEntity {

    /**
     *
     */
    @NotBlank
    @Length(max = 3)
    @Column(nullable = false, length = 3)
    private String codigo;

    /**
     *
     */
    @NotBlank
    @Length(max = 250)
    @Column(nullable = false, length = 250)
    private String nome;

    /**
     * @param codigo String
     * @param nome   String
     */
    public Banco(final @NotBlank @Length(max = 50) String codigo, final @NotBlank @Length(max = 250) String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    /**
     * @return String
     */
    @Transient
    public String getDescricao() {
        return nome;
    }

    /**
     * @param descricao String
     */
    @Transient
    public void setDescricao(final String descricao) {
        this.nome = descricao;
    }
}