package br.org.pti.api.functional.portalcompras.domain.entity.fornecedor;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.TipoCadastroTipoDocumento;
import br.org.pti.api.functional.portalcompras.domain.entity.publicacoes.Arquivo;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = ComprasApplication.FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nome", "fornecedor_id"})
})
@AuditTable(value = "documento" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
public class Documento extends Arquivo {

    @Length(max = 150)
    @Column(nullable = false, length = 150)
    @JsonAlias(value = {"_nome", "nome"})
    private String nome;

    @Column
    private Boolean aprovado;

    @Length(max = 200)
    @Column(length = 200)
    private String observacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_cadastro_tipo_documento_id")
    private TipoCadastroTipoDocumento tipoCadastroTipoDocumento;

    @PreUpdate
    @PrePersist
    protected void gerarCaminho() {
        this.caminho = "fornecedores/" + this.fornecedor.getId() + "/documentos/" + this.nome;
    }

    public String getNome() {
        return nome != null ? nome.toUpperCase() : null;
    }

    public void setNome(final String nome) {
        this.nome = nome != null ? nome.toUpperCase() : null;
    }

    public String getObservacao() {
        return observacao != null ? observacao.toUpperCase() : null;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao != null ? observacao.toUpperCase() : null;
    }
}
