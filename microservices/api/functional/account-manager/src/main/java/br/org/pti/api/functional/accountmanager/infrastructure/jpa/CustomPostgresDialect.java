package br.org.pti.api.functional.accountmanager.infrastructure.jpa;

import org.hibernate.dialect.PostgreSQL95Dialect;

/**
 * @author Emanuel Victor
 * @version 1.0.0
 * @since 2.0.0, 10/09/2020
 */
public class CustomPostgresDialect extends PostgreSQL95Dialect {

    /**
     *
     */
    public CustomPostgresDialect() {
        super.registerFunction("FILTER", new br.org.pti.api.functional.accountmanager.infrastructure.jpa.PostgresFilterFunction());
    }
}