package br.com.encoders.kriterion.builder.relation;

import br.com.encoders.kriterion.builder.renderer.QueryRender;


public final class Relation implements QueryRender {

    private JoinType type;
    private String table;
    private String alias;
    private Connection connection;

    private Relation(JoinType type, String table, String alias) {
        this.type = type;
        this.table = table;
        this.alias = alias;
    }

    public static Relation builder(JoinType type, String table) {
        return new Relation(type, table, "");
    }

    public static Relation builder(JoinType type, String table, String alias) {
        return new Relation(type, table, alias);
    }

    public Relation on(Connection connection) {
        this.connection = connection;
        return this;
    }

    @Override
    public String render() {
        var statement = new StringBuilder();

        statement
                .append(this.type.getValue())
                .append(" ")
                .append(this.table);

        if (this.alias != null && !this.alias.isEmpty()) {
            statement
                    .append(" ")
                    .append(this.alias);
        }

        if (this.connection != null) {
            statement
                    .append(" ON ")
                    .append(this.connection.render());
        }

        return statement.toString();
    }
}
