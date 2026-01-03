package br.com.encoders.kriterion.builder;

import br.com.encoders.kriterion.QueryStatement;
import br.com.encoders.kriterion.builder.condition.Condition;
import br.com.encoders.kriterion.builder.projection.Projection;
import br.com.encoders.kriterion.builder.relation.Relation;

public interface QueryBuilder {
    QueryBuilder selectAll();
    QueryBuilder select(Projection... projections);
    QueryBuilder from(String table);
    QueryBuilder from(String table, String alias);
    QueryBuilder where(Condition condition);
    QueryBuilder join(Relation... relations);
    QueryBuilder groupBy();
    QueryBuilder having();
    QueryBuilder orderBy();
    QueryStatement build();
}
