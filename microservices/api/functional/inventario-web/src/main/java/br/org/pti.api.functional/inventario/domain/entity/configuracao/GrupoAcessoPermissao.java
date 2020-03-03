package br.org.pti.api.functional.inventario.domain.entity.configuracao;

import br.org.pti.api.functional.inventario.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.api.functional.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.inventario.InventarioApplication.CONFIGURACAO;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "grupo_acesso_permissao" + AUDIT_SUFFIX, schema = CONFIGURACAO + AUDIT_SUFFIX)
@Table(schema = CONFIGURACAO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"grupo_acesso_permissao_id", "permissao_id"})
})
public class GrupoAcessoPermissao extends AbstractEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Permissao permissao;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_acesso_permissao_id")
    private GrupoAcesso grupoAcesso;

    /**
     * @param permissao Permissao
     */
    public GrupoAcessoPermissao(Permissao permissao) {
        this.permissao = permissao;
    }
}
