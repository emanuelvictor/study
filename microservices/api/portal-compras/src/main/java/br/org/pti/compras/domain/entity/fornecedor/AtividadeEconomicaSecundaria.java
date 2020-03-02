package br.org.pti.compras.domain.entity.fornecedor;


import lombok.Data;
import org.hibernate.envers.AuditTable;

import javax.persistence.*;
import java.io.Serializable;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.FORNECEDOR;

@Data
@Entity
@lombok.EqualsAndHashCode
@Table(
        schema = FORNECEDOR,
        name = "atividade_economica_secundaria",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"fornecedor_id", "atividade_economica_id"})}
)
@AuditTable(
        schema = FORNECEDOR + AUDIT_SUFFIX, value = "atividade_economica_secundaria" + AUDIT_SUFFIX
)
public class AtividadeEconomicaSecundaria implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1016812345616472876L;

    /**
     *
     */
    @Id
    @JoinColumn(name = "atividade_economica_id")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private AtividadeEconomica atividadeEconomica;

    /**
     *
     */
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

}
