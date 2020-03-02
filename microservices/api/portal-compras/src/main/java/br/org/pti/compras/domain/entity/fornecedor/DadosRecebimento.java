package br.org.pti.compras.domain.entity.fornecedor;

import br.org.pti.compras.domain.entity.endereco.Cidade;
import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.FORNECEDOR;

/**
 *
 */
@Data
@Entity
@Audited
@Table(schema = FORNECEDOR)
@EqualsAndHashCode(callSuper = false)
@AuditTable(value = "dados_recebimento" + AUDIT_SUFFIX, schema = FORNECEDOR + AUDIT_SUFFIX)
public class DadosRecebimento extends AbstractEntity {

    /**
     *
     */
    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private FormaPagamento formaPagamento;

    /**
     * {@link Cidade }
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ContaBancaria contaBancaria;

}
