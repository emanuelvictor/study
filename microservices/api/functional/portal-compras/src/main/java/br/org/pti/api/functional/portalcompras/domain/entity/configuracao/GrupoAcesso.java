package br.org.pti.api.functional.portalcompras.domain.entity.configuracao;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.EntityIdResolver;
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

@Data
@Entity
@Audited
@NoArgsConstructor
@Table(schema = ComprasApplication.CONFIGURACAO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "grupo_acesso" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CONFIGURACAO + ComprasApplication.AUDIT_SUFFIX)
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
