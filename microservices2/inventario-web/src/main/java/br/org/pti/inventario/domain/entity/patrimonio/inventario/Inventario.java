package br.org.pti.inventario.domain.entity.patrimonio.inventario;

import br.org.pti.inventario.domain.entity.AbstractEntity;
import br.org.pti.inventario.domain.entity.EntityIdResolver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import static br.org.pti.inventario.Application.AUDIT_SUFFIX;
import static br.org.pti.inventario.Application.PATRIMONIO;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
@Data
@Entity
@Audited
@Table(schema = PATRIMONIO)
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = "inventario" + AUDIT_SUFFIX, schema = PATRIMONIO + AUDIT_SUFFIX)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Inventario.class,
        resolver = EntityIdResolver.class)
public class Inventario extends AbstractEntity implements Serializable {

    /**
     *
     */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String nome;

    /**
     *
     */
    @EqualsAndHashCode.Exclude
    @OneToMany(targetEntity = CentroCustoInventario.class, mappedBy = "inventario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CentroCustoInventario> centrosCusto;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    /**
     *
     */
    public Inventario() {
    }

    /**
     * @param id   long
     * @param nome String
     */
    public Inventario(final long id, @NotBlank final String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @param id          long
     * @param nome        String
     * @param dataInicio  LocalDate
     * @param dataTermino LocalDate
     */
    public Inventario(final long id, @NotBlank final String nome, @NotNull final LocalDate dataInicio, @NotNull final LocalDate dataTermino) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }
}
