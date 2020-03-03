package br.org.pti.api.functional.inventario.domain.entity.patrimonio.inventario;

import br.org.pti.api.functional.inventario.domain.entity.AbstractEntity;
import br.org.pti.api.functional.inventario.domain.entity.EntityIdResolver;
import br.org.pti.api.functional.inventario.domain.entity.pessoal.CentroCusto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

import static br.org.pti.api.functional.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.inventario.InventarioApplication.PATRIMONIO;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@JsonIdentityInfo(
        property = "id",
        resolver = EntityIdResolver.class,
        scope = CentroCustoInventario.class,
        generator = ObjectIdGenerators.PropertyGenerator.class
)
@EqualsAndHashCode(callSuper = true)
@Table(schema = PATRIMONIO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"inventario_id", "centro_custo_id"})
})
@AuditTable(value = "centro_custo_inventario" + AUDIT_SUFFIX, schema = PATRIMONIO + AUDIT_SUFFIX)
public class CentroCustoInventario extends AbstractEntity {

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = Executor.class, mappedBy = "centroCustoInventario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Executor> executores;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CentroCustoInventarioStatus status;

    /**
     *
     */
    @Column(name = "data_termino_extendida")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTerminoExtendida;

    /**
     *
     */
    public CentroCustoInventario() {
    }

    /**
     *
     */
    @PrePersist
    public void prePersist() {
        this.status = CentroCustoInventarioStatus.EM_EXECUCAO;
    }

    /**
     * @return
     */
    public Boolean isTerminado() {
        return this.inventario.getDataTermino().isBefore(LocalDate.now());
    }
}
