package br.org.pti.compras.domain.entity.publicacoes;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.PUBLICACAO;

@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = PUBLICACAO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "publicacao_id"})
})
@AuditTable(value = "anexo" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public class Anexo extends Arquivo {

    /**
     *
     */
    private static final long serialVersionUID = 1016827183036471234L;

    /**
     *
     */
    @Length(max = 150)
    @JsonAlias(value = {"_nome", "nome"})
    @Column(nullable = false, length = 150)
    private String nome;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    /**
     *
     */
    @PreUpdate
    @PrePersist
    protected void gerarCaminho() {
        if (publicacao instanceof AvisoEdital)
            this.caminho = "avisos-editais/" + this.publicacao.getId() + "/anexos/" + this.nome;
        else if (publicacao instanceof AvisoContratacao)
            this.caminho = "avisos-contratacoes/" + this.publicacao.getId() + "/anexos/" + this.nome;
        else if (publicacao instanceof ExtratoContrato)
            this.caminho = "extratos-contratos/" + this.publicacao.getId() + "/anexos/" + this.nome;
        else this.caminho = "publicacoes/" + this.publicacao.getId() + "/anexos/" + this.nome;
    }

}
