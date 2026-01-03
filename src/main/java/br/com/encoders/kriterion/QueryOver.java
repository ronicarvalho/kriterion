package br.com.encoders.kriterion;

import br.com.encoders.kriterion.builder.QueryBuilder;
import br.com.encoders.kriterion.builder.condition.Condition;
import br.com.encoders.kriterion.builder.projection.Projection;
import br.com.encoders.kriterion.builder.relation.Relation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class QueryOver implements QueryBuilder {

    private StringBuilder select;
    private StringBuilder from;
    private StringBuilder join;
    private StringBuilder where;

    private QueryCompose compositor;

    private QueryOver() {
        this.select = new StringBuilder();
        this.from = new StringBuilder();
        this.join = new StringBuilder();
        this.where = new StringBuilder();
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
        this.select.append("SELECT *");
        return this;
    }

    @Override
    public QueryBuilder select(Projection... projections) {
        this.throwIfComposed("SELECT", this.compositor.isSelectComposed());
        this.compositor.composeSelect();
        this.select
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
        this.from
                .append(" FROM ")
                .append(alias.isEmpty() ? table : table + " " + alias);
        return this;
    }

    @Override
    public QueryBuilder where(Condition condition) {
        this.throwIfComposed("WHERE", this.compositor.isWhereComposed());
        this.compositor.composeWhere();
        this.where
                .append(" ")
                .append("WHERE ")
                .append(condition.render());
        return this;
    }

    @Override
    public QueryBuilder join(Relation... relations) {
        this.throwIfComposed("JOIN", this.compositor.isJoinComposed());
        this.compositor.composeJoin();
        this.join
                .append(" ")
                .append(
                        Arrays.stream(relations)
                                .map(Relation::render)
                                .collect(Collectors.joining(" "))
        );
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
        this.validate();
        var statement = this.composeStatement();
        this.dispose();
        return new QueryStatement(statement.trim());
    }

    private void validate() {
        if (!this.compositor.isSelectComposed()) {
            throw new IllegalStateException("The select must be composed");
        }

        if (!this.compositor.isFromComposed()) {
            throw new IllegalStateException("The from must be composed");
        }
    }

    private void dispose() {
        this.select = new StringBuilder();
        this.from = new StringBuilder();
        this.join = new StringBuilder();
        this.where = new StringBuilder();
        this.compositor = new QueryCompose();
    }

    private String composeStatement() {
        var writer = new StringBuilder();

        writer.append(this.select.toString());
        writer.append(this.from.toString());

        if (this.compositor.isJoinComposed())
            writer.append(this.join.toString());

        if (this.compositor.isWhereComposed())
            writer.append(this.where.toString());

        return writer.toString();
    }
}
