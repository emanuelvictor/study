package br.org.pti.inventario.domain.entity.patrimonio;

import br.org.pti.inventario.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static br.org.pti.inventario.InventarioApplication.AUDIT_SUFFIX;
import static br.org.pti.inventario.InventarioApplication.PATRIMONIO;

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
@AuditTable(value = "localizacao" + AUDIT_SUFFIX, schema = PATRIMONIO + AUDIT_SUFFIX)
public class Localizacao extends AbstractEntity {

    /**
     *
     */
    public static final String DEFAULT_LOCATION_TO_NOT_LOCALIZATED = "001142";

    /**
     *
     */
    @Column
    private String recno;

    /**
     *
     */
    @Column(unique = true)
    private String codigo;

    /**
     *
     */
    @Column
    private String descricao;

    /**
     *
     */
    @Column
    private String tipoLocalizacao;

}
