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

import static br.org.pti.compras.Application.AUDIT_SUFFIX;
import static br.org.pti.compras.Application.CADASTRO;

@Data
@Entity
@Audited
@AuditTable(value = "categoria" + AUDIT_SUFFIX, schema = CADASTRO + AUDIT_SUFFIX)
@NoArgsConstructor
@Table(schema = CADASTRO)
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
