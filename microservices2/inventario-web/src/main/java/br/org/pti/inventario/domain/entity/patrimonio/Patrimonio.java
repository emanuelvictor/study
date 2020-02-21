package br.org.pti.inventario.domain.entity.patrimonio;

import br.org.pti.inventario.domain.entity.AbstractEntity;
import br.org.pti.inventario.domain.entity.EntityIdResolver;
import br.org.pti.inventario.domain.entity.patrimonio.inventario.CentroCustoInventario;
import br.org.pti.inventario.domain.entity.pessoal.CentroCusto;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AuditTable(value = PATRIMONIO + AUDIT_SUFFIX, schema = PATRIMONIO + AUDIT_SUFFIX)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = Patrimonio.class,
        resolver = EntityIdResolver.class)
@Table(schema = PATRIMONIO, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"numero", "item", "codigo_base"})
})
public class Patrimonio extends AbstractEntity {

    /**
     *
     */
    @NotBlank
    @JsonAlias("plaqueta")
    @Column(nullable = false)
    private String numero;

    /**
     *
     */
    @Column(nullable = false)
    private String item;

    /**
     *
     */
    @Column(name = "codigo_base")
    private String codigoBase;

    /**
     *
     */
    @Column
    private String descricao;

    /**
     *
     */
    @Column
    private String complemento;

    /**
     *
     */
    @Column
    private String observacao;

    /**
     *
     */
    @Column
    private String capacidade;

    /**
     *
     */
    @Column
    private String departamento;

    /**
     *
     */
    @Column(nullable = false)
    private boolean sobraFisica;

    /**
     *
     */
    @Column(nullable = false)
    private boolean encontrado;

    /**
     * Desnormalizado, deveria ser único, ou será que não?!
     */
    @Column
    private String numeroSerie;

    /**
     *
     */
    @Column
    private String modelo;

    /**
     *
     */
    @Column
    private String marca;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Localizacao localizacaoAnterior;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Localizacao localizacao;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private CentroCusto centroCustoAnterior;

    /**
     *
     */
    @JoinColumn(name = "centro_custo_inventario_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private CentroCustoInventario centroCustoInventario;

    /**
     * @param id
     * @param numero
     * @param item
     * @param codigoBase
     * @param descricao
     * @param complemento
     * @param observacao
     * @param capacidade
     * @param departamento
     * @param encontrado
     * @param numeroSerie
     * @param modelo
     * @param localizacao
     * @param centroCustoAnterior
     * @param centroCustoInventario
     */
    public Patrimonio(final long id, final @NotBlank String numero, final String item,
                      final String codigoBase, final String descricao, final String complemento,
                      final String observacao, final String capacidade, final String departamento,
                      final boolean encontrado, final String numeroSerie, final String modelo,
                      final String marca, final boolean sobraFisica, final Localizacao localizacao, final Localizacao localizacaoAnterior,
                      final CentroCusto centroCustoAnterior, final CentroCustoInventario centroCustoInventario) {
        this.id = id;
        this.numero = numero;
        this.item = item;
        this.codigoBase = codigoBase;
        this.descricao = descricao;
        this.complemento = complemento;
        this.observacao = observacao;
        this.capacidade = capacidade;
        this.departamento = departamento;
        this.encontrado = encontrado;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.marca = marca;
        this.sobraFisica = sobraFisica;
        this.localizacao = localizacao;
        this.localizacaoAnterior = localizacaoAnterior;
        this.centroCustoAnterior = centroCustoAnterior;
        this.centroCustoInventario = centroCustoInventario;
    }

    /**
     * @return
     */
    public String getPlaqueta() {
        return this.numero;
    }

    /**
     *
     */
    @PreUpdate
    @PrePersist
    public void prePersistAndPreUpdate() {

        if (item == null)
            item = "0001";

        // Sempre que for sobra física, o status será como "encontrado"
        if (sobraFisica)
            encontrado = true;
    }

//    /**
//     * @return
//     */
//    public boolean transferido() {
//        return this.getCentroCustoAnterior() != null && !this.getCentroCustoAnterior().getCodigo().equals(this.centroCustoInventario.getCentroCusto().getCodigo());
//    }

//    /**
//     *
//     * @param encontrado
//     */
//    public void setEncontrado(boolean encontrado) {
//        this.encontrado = !this.transferido() && encontrado;
//    }
}
