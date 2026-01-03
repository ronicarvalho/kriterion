package br.com.encoders.kriterion.builder;

import br.com.encoders.kriterion.QueryStatement;
import br.com.encoders.kriterion.builder.condition.Condition;
import br.com.encoders.kriterion.builder.projection.Projection;

public interface QueryBuilder {
    QueryBuilder selectAll();
    QueryBuilder select(Projection... projections);
    QueryBuilder from(String table);
    QueryBuilder from(String table, String alias);
    QueryBuilder where(Condition condition);
    QueryBuilder join();
    QueryBuilder groupBy();
    QueryBuilder having();
    QueryBuilder orderBy();
    QueryStatement build();
}
