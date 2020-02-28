package br.org.pti.authorizationserver.domain.entities.configuration;

import br.org.pti.authorizationserver.domain.entities.PersistentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"grupo_acesso_permissao_id", "permissao_id"})
})
public class AccessGroupPermission extends PersistentEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Permission permissao;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_acesso_permissao_id")
    private AccessGroup grupoAcesso;

    /**
     * @param permissao Permissao
     */
    public AccessGroupPermission(Permission permissao) {
        this.permissao = permissao;
    }
}
