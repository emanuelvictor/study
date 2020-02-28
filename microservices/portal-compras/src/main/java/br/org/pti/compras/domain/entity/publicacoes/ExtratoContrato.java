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
@AuditTable(value = "extrato_contrato" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public class ExtratoContrato extends Publicacao {

    @Length(max = 20)
    @NotNull
    @Column(unique = true, nullable = false, length = 20)
    protected String numeroProcesso;

    @Length(max = 150)
    @Column(length = 150)
    private String razaoSocial;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private InstrumentoJuridico instrumentoJuridico;

    public ExtratoContrato() {
    }

    public ExtratoContrato(final long id) {
        super(id);
    }

    /**
     * @param id long
     * @param numeroProcesso String
     * @param razaoSocial String
     * @param instrumentoJuridico InstrumentoJuridico
     */
    public ExtratoContrato(final long id, final @Length(max = 20) @NotNull String numeroProcesso, final @Length(max = 150) String razaoSocial, final @NotNull InstrumentoJuridico instrumentoJuridico) {
        super(id);
        this.numeroProcesso = numeroProcesso;
        this.razaoSocial = razaoSocial;
        this.instrumentoJuridico = instrumentoJuridico;
    }

    @PrePersist
    public void prePersist() {
        this.dataPublicacao = LocalDate.now();
    }
}
