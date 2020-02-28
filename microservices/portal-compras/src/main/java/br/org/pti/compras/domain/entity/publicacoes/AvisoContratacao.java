package br.org.pti.compras.domain.entity.publicacoes;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "aviso_contratacao" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public class AvisoContratacao extends Publicacao {

    @NotNull
    @Length(max = 20)
    @Column(unique = true, nullable = false, length = 20)
    protected String numeroProcesso;

    @NotNull
    @Length(max = 20)
    @Column(nullable = false, length = 20)
    private String numeroModalidade;

    @Length(max = 150)
    @Column(length = 150)
    private String razaoSocial;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Modalidade modalidade;

    public AvisoContratacao() {
    }

    public AvisoContratacao(final long id) {
        this.id = id;
    }

    public AvisoContratacao(final long id, final @NotNull @Length(max = 20) String numeroModalidade, final @NotNull Modalidade modalidade, final @NotNull @Length(max = 20) String numeroProcesso, final @Length(max = 150) String razaoSocial) {
        super(id);
        this.numeroModalidade = numeroModalidade;
        this.modalidade = modalidade;
        this.numeroProcesso = numeroProcesso;
        this.razaoSocial = razaoSocial;
    }

    @PrePersist
    public void prePersist() {
        this.dataPublicacao = LocalDate.now();
    }
}
