package br.org.pti.compras.domain.entity.fornecedor;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.compras.ComprasApplication.FORNECEDOR;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Data
@Entity
@lombok.EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(schema = FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"code", "text"},
                name = "atividade_economica_codigo_descricao_unique_constraint"
        )
})
@Audited(targetAuditMode = NOT_AUDITED)
public class AtividadeEconomica {

    @Id
    private String code;

    @Column(nullable = false)
    private String text;

}
