package br.com.encoders.kriterion;

import br.com.encoders.kriterion.builder.QueryBuilder;
import br.com.encoders.kriterion.builder.condition.Condition;
import br.com.encoders.kriterion.builder.projection.Projection;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class QueryOver implements QueryBuilder {

    private final StringBuilder writer;
    private final QueryCompose compositor;

    private QueryOver() {
        this.writer = new StringBuilder();
        this.compositor = new QueryCompose();
    }

    public static QueryOver builder() {
        return new QueryOver();
    }

    private void throwIfComposed(String section, boolean composed) {
        if (composed) {
            throw new IllegalStateException(section + " already defined");
        }
    }

    @Override
    public QueryBuilder selectAll() {
        this.throwIfComposed("SELECT", this.compositor.isSelectComposed());
        this.compositor.composeSelect();
        this.writer.append("SELECT *");
        return this;
    }

    @Override
    public QueryBuilder select(Projection... projections) {
        this.throwIfComposed("SELECT", this.compositor.isSelectComposed());
        this.compositor.composeSelect();
        this.writer
                .append("SELECT ")
                .append(
                        Arrays.stream(projections)
                                .map(Projection::render)
                                .collect(Collectors.joining(", "))
                );
        return this;
    }

    @Override
    public QueryBuilder from(String table) {
        return this.from(table, "");
    }

    @Override
    public QueryBuilder from(String table, String alias) {
        this.throwIfComposed("FROM", this.compositor.isFromComposed());
        this.compositor.composeFrom();
        this.writer
                .append(" FROM ")
                .append(alias.isEmpty() ? table : table + " " + alias)
                .append(" ");
        return this;
    }

    @Override
    public QueryBuilder where(Condition condition) {
        this.throwIfComposed("WHERE", this.compositor.isWhereComposed());
        this.compositor.composeWhere();
        this.writer
                .append("WHERE ")
                .append(condition.render());

        return this;
    }

    @Override
    public QueryBuilder join() {
        return this;
    }

    @Override
    public QueryBuilder groupBy() {
        return this;
    }

    @Override
    public QueryBuilder having() {
        return this;
    }

    @Override
    public QueryBuilder orderBy() {
        return this;
    }

    @Override
    public QueryStatement build() {
        var sql = !this.writer.isEmpty()
                ? this.writer.toString() : "";
        return new QueryStatement(sql.trim());
    }
}
