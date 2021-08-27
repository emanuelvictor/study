package br.com.emanuelvictor.books.infrastructure.hibernate.dialect;

import br.com.emanuelvictor.books.infrastructure.hibernate.functions.PostgreSQLFilterFunction;
import org.hibernate.dialect.PostgreSQL9Dialect;

public class PostgreSQLDialect extends PostgreSQL9Dialect
{
	public PostgreSQLDialect() 
	{
		super.registerFunction("FILTER", new PostgreSQLFilterFunction());
	}
}
