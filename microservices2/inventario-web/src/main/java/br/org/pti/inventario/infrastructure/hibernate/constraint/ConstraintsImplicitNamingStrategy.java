package br.org.pti.inventario.infrastructure.hibernate.constraint;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitForeignKeyNameSource;
import org.hibernate.boot.model.naming.ImplicitIndexColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

/**
 * Custom implicit naming strategy for hibernate 5.
 * <p/>
 * Necessary because the new hibernate version introduced new semantics and new interfaces and configuration of
 * the naming strategy which is responsible for the determination of the names of database objects (tables, fields,
 * constraints, indices and others) that are not explicitly named.
 *
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class ConstraintsImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    /**
     *
     */
    public ConstraintsImplicitNamingStrategy() {
    }

    /**
     * @param source ImplicitIndexColumnNameSource
     * @return Identifier
     */
    @Override
    public Identifier determineListIndexColumnName(final ImplicitIndexColumnNameSource source) {
        return toIdentifier(
                transformAttributePath(source.getPluralAttributePath()) + "_ORDER",
                source.getBuildingContext()
        );
    }

    /**
     * Replicate hibernate 4.3's constraint naming strategy in EJBNamingStrategy, which differs in the following
     * ways:
     * - include underscore between prefix and hash value
     * - prefix unique indices also with UK_ instead of IDX_
     * - do not include the referenced table name in the hash value generation for foreign keys (do not use
     * generateHashedFkName method but instead stay with generateHashedConstraintName).
     *
     * @param source ImplicitIndexColumnNameSource
     * @return Identifier
     */
    @Override
    public Identifier determineForeignKeyName(final ImplicitForeignKeyNameSource source) {
        final String tableName = source.getTableName().getText();
        final String referencedTableName = source.getColumnNames().get(0).getText();//source.getReferencedTableName().getText();

        return toIdentifier("FK_" + tableName + "_" + referencedTableName, source.getBuildingContext());
    }

}
