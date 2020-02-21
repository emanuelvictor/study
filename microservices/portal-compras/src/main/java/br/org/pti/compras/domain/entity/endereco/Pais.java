package br.org.pti.compras.domain.entity.endereco;

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
import javax.validation.constraints.NotEmpty;

import static br.org.pti.compras.Application.AUDIT_SUFFIX;
import static br.org.pti.compras.Application.ENDERECO;

/**
 *
 */
@Data
@Entity
@Audited
@NoArgsConstructor
@Table(schema = ENDERECO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "pais" + AUDIT_SUFFIX, schema = ENDERECO + AUDIT_SUFFIX)
public class Pais extends AbstractEntity {

    private static final long serialVersionUID = -7513339061739700255L;

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    private String nome;

}
