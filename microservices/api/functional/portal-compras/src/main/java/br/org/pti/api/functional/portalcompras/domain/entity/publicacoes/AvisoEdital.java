package br.org.pti.api.functional.portalcompras.domain.entity.publicacoes;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Audited
@Table(schema = ComprasApplication.PUBLICACAO)
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = AvisoEdital.class,
        resolver = EntityIdResolver.class)
@AuditTable(value = "aviso_edital" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.PUBLICACAO + ComprasApplication.AUDIT_SUFFIX)
public class AvisoEdital extends Publicacao {

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate prazoPropostas;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @NotNull
    @Length(max = 20)
    @Column(unique = true, nullable = false, length = 20)
    private String numeroProcesso;

    @NotNull
    @Length(max = 20)
    @Column(unique = true, nullable = false, length = 20)
    private String numeroEdital;

    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = CategoriaAvisoEdital.class, mappedBy = "avisoEdital", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CategoriaAvisoEdital> categoriasAvisosEditais;

    @Transient
    private String categoria;

    public AvisoEdital() {
    }

    public AvisoEdital(final long id) {
        this.id = id;
    }

    public AvisoEdital(final long id, @NotNull final LocalDate prazoPropostas, @NotNull final Status status, @NotNull final String numeroProcesso, @NotNull final String numeroEdital, final LocalDate dataPublicacao, final String objeto, final LocalDateTime updated, final String categoria) {
        this.id = id;
        this.prazoPropostas = prazoPropostas;
        this.status = status;
        this.numeroProcesso = numeroProcesso;
        this.numeroEdital = numeroEdital;
        this.dataPublicacao = dataPublicacao;
        this.objeto = objeto;
        this.categoria = categoria;
        this.updated = updated;
    }
}
