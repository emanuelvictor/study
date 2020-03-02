package br.org.pti.compras.domain.entity.publicacoes;

import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.PUBLICACAO;

@Data
@Entity
@Audited
@Table(schema = PUBLICACAO)
@lombok.EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@AuditTable(value = "publicacao" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public class Publicacao extends AbstractEntity {

    @NotNull
    @Length(max = 500)
    @Column(nullable = false, length = 500)
    protected String objeto;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataPublicacao;

    public Publicacao() {
    }

    public Publicacao(final long id) {
        this.id = id;
    }
}
