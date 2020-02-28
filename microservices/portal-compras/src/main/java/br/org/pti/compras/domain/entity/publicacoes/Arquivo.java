package br.org.pti.compras.domain.entity.publicacoes;

import br.org.pti.compras.domain.entity.publicacoes.generic.AbstractArquivo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.PUBLICACAO;

@Data
@Entity
@Audited
@Table(schema = PUBLICACAO)
@JsonIgnoreProperties({"conteudo"})
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@AuditTable(value = "arquivo" + AUDIT_SUFFIX, schema = PUBLICACAO + AUDIT_SUFFIX)
public abstract class Arquivo extends AbstractArquivo {

    /**
     *
     */
    private static final long serialVersionUID = 1016898753036471234L;

    /**
     * Caminho do arquivo para acesso direto.
     */
    @NotNull
    @Column(nullable = false)
    protected String caminho;

    /**
     * Link para acesso externo do anexo.
     */
    @Length(max = 500)
    @Column(length = 500)
    protected String link;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    protected boolean externo = false;

    /**
     *
     */
    @NotNull
    @Length(max = 255)
    @Column(nullable = false)
    protected String type;

    /**
     * Armazena o conteúdo em si
     */
    @Lob
    @JsonProperty
    @Basic(fetch = FetchType.EAGER)
    protected byte[] conteudo;

    /**
     * Executado antes da persistência/atualização na base de dados.
     */
    @PreUpdate
    @PrePersist
    private void validateLength() {
        // Se o arquivo for externo, então o conteúdo em bytes deve ser removido da base de dados.
        if (this.externo) {
            this.conteudo = null;
            this.type = "url";
        }
        // Se o arquivo for interno, então o link deve ser removido da base de dados.
        // Também valida o tamanho do arquivo.
        else {
            link = null;
            Assert.notNull(conteudo, "Arquivo sem conteúdo");
            Assert.isTrue(conteudo.length <= 5242880, "O arquivo tem um conteúdo com mais de 5MB de tamanho em disco");
        }
    }
}
