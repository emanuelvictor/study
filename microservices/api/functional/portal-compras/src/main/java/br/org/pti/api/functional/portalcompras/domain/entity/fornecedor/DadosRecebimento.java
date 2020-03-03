package br.org.pti.api.functional.portalcompras.domain.entity.fornecedor;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.endereco.Cidade;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 */
@Data
@Entity
@Audited
@Table(schema = ComprasApplication.FORNECEDOR)
@EqualsAndHashCode(callSuper = false)
@AuditTable(value = "dados_recebimento" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
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
