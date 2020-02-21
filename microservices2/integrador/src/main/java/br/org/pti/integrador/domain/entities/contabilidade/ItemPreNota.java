package br.org.pti.integrador.domain.entities.contabilidade;

import br.org.pti.integrador.domain.entities.compras.Produto;
import br.org.pti.integrador.infrastructure.utils.jpa.ProtheusPersistentEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Classe que representa os itens da nota fiscal
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.1
 * @since 1.0.0, 19/09/17
 */
@Entity
@Table(name = "zi3010")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ItemPreNota extends ProtheusPersistentEntity {

    @Id
    @Getter
    @Column(name = "r_e_c_n_o_", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ZI3_SEQ")
    @SequenceGenerator(name = "ZI3_SEQ", sequenceName = "ZI3010_SEQ", allocationSize = 1)
    private Long recno;

    @Getter
    @Setter
    @Column(name = "zi3_item", nullable = false)
    @NotBlank(message = "item-pre-nota.item-nota-vazio")
    private String itemNota;
    @Getter
    @Setter
    @Column(name = "zi3_quant", nullable = false)
    @NotNull(message = "item-pre-nota.quantidade-invalido")
    private long quantidade;
    @Getter
    @Setter
    @Column(name = "zi3_prcven", nullable = false)
    @NotNull(message = "item-pre-nota.valor-venda-invalido")
    private BigDecimal valorVenda;
    @Getter
    @Setter
    @Column(name = "zi3_total", nullable = false)
    @NotNull(message = "item-pre-nota.valor-total-invalido")
    private BigDecimal valorTotal;
    @Getter
    @Setter
    @Column(name = "zi3_desc", nullable = false)
    @NotNull(message = "item-pre-nota.perdentual-desconto-invalido")
    private BigDecimal percentualDesconto;
    @Getter
    @Setter
    @Column(name = "zi3_descon", nullable = false)
    @NotNull(message = "item-pre-nota.valor-desconto-invalido")
    private BigDecimal valorDesconto;
    @Getter
    @Setter
    @Column(name = "zi3_prunit", nullable = false)
    @NotNull(message = "item-pre-nota.valor-unitario-invalido")
    private BigDecimal valorUnitario;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "zi3_cod", referencedColumnName = "b1_cod")
    @NotNull(message = "item-pre-nota.produto-invalido")
    private Produto produto;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "zi3_doc", referencedColumnName = "zi2_doc")
    @NotNull(message = "item-pre-nota.nota-fiscal-invalida")
    @JsonIgnore
    private PreNota notaFiscal;

    /**
     * Construtor da classe
     */
    public ItemPreNota() {}
}
