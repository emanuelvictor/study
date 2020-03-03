package br.org.pti.api.functional.portalcompras.domain.entity.fornecedor;

import br.org.pti.api.functional.portalcompras.ComprasApplication;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Banco;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import br.org.pti.api.functional.portalcompras.domain.entity.cadastros.Banco;
import br.org.pti.api.functional.portalcompras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static br.org.pti.api.functional.portalcompras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.api.functional.portalcompras.ComprasApplication.FORNECEDOR;


/**
 *
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = ComprasApplication.FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"agencia", "digito", "numero", "banco_id"})
})
@AuditTable(value = "conta_bancaria" + ComprasApplication.AUDIT_SUFFIX, schema = ComprasApplication.FORNECEDOR + ComprasApplication.AUDIT_SUFFIX)
public class ContaBancaria extends AbstractEntity {

    /**
     *
     */
    @Column
    @Enumerated(EnumType.ORDINAL)
    private TipoContaBancaria tipoContaBancaria;

//    /**
//     *
//     */
//    @Column
//    private String identificacao;

    /**
     *
     */
    @Column(length = 4)
    @Length(max = 4, min = 4)
    private String agencia;

    /**
     *
     */
    @Length(max = 2)
    @Column(length = 2)
    private String digito;

    /**
     *
     */
    @Length(max = 20)
    @Column(length = 20)
    private String numero;

    /**
     *
     */
    @ManyToOne
    private Banco banco;

}
