package br.org.pti.api.functional.inventario.infrastructure.hibernate.dialect;

import br.org.pti.api.functional.inventario.infrastructure.hibernate.functions.PostgreSQLFilterFunction;
import org.hibernate.dialect.PostgreSQL9Dialect;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 1.0.0, 10/09/2019
 */
public class PostgreSQLDialect extends PostgreSQL9Dialect {
    /**
     *
     */
    public PostgreSQLDialect() {
        super.registerFunction("FILTER", new PostgreSQLFilterFunction());
    }
}
