package br.org.pti.compras.domain.entity.fornecedor;

import br.org.pti.compras.domain.entity.cadastros.Banco;
import br.org.pti.compras.domain.entity.generic.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import static br.org.pti.compras.ComprasApplication.AUDIT_SUFFIX;
import static br.org.pti.compras.ComprasApplication.FORNECEDOR;


/**
 *
 */
@Data
@Entity
@Audited
@EqualsAndHashCode(callSuper = true)
@Table(schema = FORNECEDOR, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"agencia", "digito", "numero", "banco_id"})
})
@AuditTable(value = "conta_bancaria" + AUDIT_SUFFIX, schema = FORNECEDOR + AUDIT_SUFFIX)
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
