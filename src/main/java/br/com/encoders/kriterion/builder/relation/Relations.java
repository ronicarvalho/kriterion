package br.com.encoders.kriterion.builder.relation;

public final class Relations {
    public static Relation inner(String table) {
        return Relation.builder(JoinType.INNER, table);
    }

    public static Relation inner(String table, String alias) {
        return Relation.builder(JoinType.INNER, table, alias);
    }

    public static Relation left(String table) {
        return Relation.builder(JoinType.LEFT, table);
    }

    public static Relation left(String table, String alias) {
        return Relation.builder(JoinType.LEFT, table, alias);
    }

    public static Relation right(String table) {
        return Relation.builder(JoinType.RIGHT, table);
    }

    public static Relation right(String table, String alias) {
        return Relation.builder(JoinType.RIGHT, table, alias);
    }

    public static Relation full(String table) {
        return Relation.builder(JoinType.FULL, table);
    }

    public static Relation full(String table, String alias) {
        return Relation.builder(JoinType.FULL, table, alias);
    }

    public static Relation cross(String table) {
        return Relation.builder(JoinType.CROSS, table);
    }

    public static Relation cross(String table, String alias) {
        return Relation.builder(JoinType.CROSS, table, alias);
    }
}
