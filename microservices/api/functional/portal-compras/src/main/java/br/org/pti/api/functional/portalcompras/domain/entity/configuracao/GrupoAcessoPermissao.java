package br.org.pti.api.functional.portalcompras.domain.entity.configuracao;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "grupo_acesso_permissao" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.CONFIGURACAO + ComprasApplication.AUDIT_SUFFIX)
@Table(schema = ComprasApplication.CONFIGURACAO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"grupo_acesso_permissao_id", "permissao_id"})
})
public class GrupoAcessoPermissao extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Permissao permissao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_acesso_permissao_id")
    private GrupoAcesso grupoAcesso;

    public GrupoAcessoPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
