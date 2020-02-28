package br.org.pti.compras.domain.entity.configuracao;

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
import java.util.Set;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.CONFIGURACAO;

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(schema = CONFIGURACAO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "grupo_acesso" + AUDIT_SUFFIX, schema = CONFIGURACAO + AUDIT_SUFFIX)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = GrupoAcesso.class,
        resolver = EntityIdResolver.class)
public class GrupoAcesso extends AbstractEntity {

    //    @NotBlank
    @Length(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    //    @NotNull
    @Column(nullable = false)
    private boolean ativo = true;

    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = GrupoAcessoPermissao.class, mappedBy = "grupoAcesso", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<GrupoAcessoPermissao> gruposAcessoPermissoes;

}
