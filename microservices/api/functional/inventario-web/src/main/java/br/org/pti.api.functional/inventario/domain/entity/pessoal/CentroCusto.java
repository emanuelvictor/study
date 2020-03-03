package br.org.pti.api.functional.inventario.domain.entity.pessoal;

import br.org.pti.api.functional.inventario.domain.entity.AbstractEntity;
import br.org.pti.api.functional.inventario.domain.entity.EntityIdResolver;
import br.org.pti.api.functional.inventario.domain.entity.configuracao.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static br.org.pti.api.functional.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.inventario.InventarioApplication.PESSOAL;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@Table(schema = PESSOAL)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = PESSOAL + AUDIT_SUFFIX, schema = PESSOAL + AUDIT_SUFFIX)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = CentroCusto.class,
        resolver = EntityIdResolver.class
)
public class CentroCusto extends AbstractEntity {

    /**
     *
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "gestor_id")
    private Usuario gestor;

    /**
     *
     */
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String codigo;

    /**
     *
     */
    @NotNull
    @NotBlank
    @Size(max = 500)
    @Column(nullable = false)
    private String descricao;

    /**
     *
     */
    public CentroCusto() {
    }

    /**
     * @param id long
     */
    public CentroCusto(final long id) {
        this.id = id;
    }

    /**
     * @param descricaoCentroCusto String
     */
    public void setDescricaoCentroCusto(final String descricaoCentroCusto) {
        this.descricao = descricaoCentroCusto;
    }

    /**
     * @param centroCusto String
     */
    public void setCentroCusto(final String centroCusto) {
        this.codigo = centroCusto;
    }

}
