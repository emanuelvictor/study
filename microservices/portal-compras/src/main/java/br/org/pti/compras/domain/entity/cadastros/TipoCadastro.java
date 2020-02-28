package br.org.pti.compras.domain.entity.cadastros;

import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import br.org.pti.compras.domain.entity.generic.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CADASTRO;

@Data
@Entity
@Audited
@NoArgsConstructor
@AuditTable(value = "tipo_cadastro" + AUDIT_SUFFIX, schema = CADASTRO + AUDIT_SUFFIX)
@Table(schema = CADASTRO)
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = TipoCadastro.class,
        resolver = EntityIdResolver.class)
public class TipoCadastro extends AbstractEntity {

    /**
     *
     */
    @NotBlank
    @Length(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = TipoCadastroTipoDocumento.class, mappedBy = "tipoCadastro", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TipoCadastroTipoDocumento> documentos;

    /**
     * @param id {Long}
     */
    public TipoCadastro(final long id) {
        this.id = id;
    }
}
