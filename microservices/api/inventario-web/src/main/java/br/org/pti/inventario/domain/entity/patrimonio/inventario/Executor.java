package br.org.pti.inventario.domain.entity.patrimonio.inventario;

import br.org.pti.inventario.domain.entity.AbstractEntity;
import br.org.pti.inventario.domain.entity.EntityIdResolver;
import br.org.pti.inventario.domain.entity.configuracao.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.inventario.InventarioApplication.PATRIMONIO;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(property = "id", scope = Executor.class, resolver = EntityIdResolver.class, generator = ObjectIdGenerators.PropertyGenerator.class)
@AuditTable(value = "executor" + AUDIT_SUFFIX, schema = PATRIMONIO + AUDIT_SUFFIX)
@Table(schema = PATRIMONIO, uniqueConstraints = {@UniqueConstraint(columnNames = {"centro_custo_inventario_id", "usuario_id"})})
public class Executor extends AbstractEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "centro_custo_inventario_id")
    private CentroCustoInventario centroCustoInventario;

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     *
     */
    @Column
    private Boolean avulso;

    /**
     *
     */
    public Executor() {
    }

}
