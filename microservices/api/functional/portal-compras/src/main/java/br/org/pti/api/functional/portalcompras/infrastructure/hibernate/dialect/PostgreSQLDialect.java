package br.org.pti.api.functional.portalcompras.infrastructure.hibernate.dialect;

import br.org.pti.api.functional.portalcompras.infrastructure.hibernate.functions.PostgreSQLFilterFunction;
import org.hibernate.dialect.PostgreSQL9Dialect;

public class PostgreSQLDialect extends PostgreSQL9Dialect {
    public PostgreSQLDialect() {
        super.registerFunction("FILTER", new PostgreSQLFilterFunction());
    }
}
