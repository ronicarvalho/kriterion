package br.com.encoders.kriterion.builder.relation;

public final class Connections {
    public static Connection eq(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, "=", rightColumn);
    }

    public static Connection neq(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, "<>", rightColumn);
    }

    public static Connection gt(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, ">", rightColumn);
    }

    public static Connection gte(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, ">=", rightColumn);
    }

    public static Connection lt(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, "<", rightColumn);
    }

    public static Connection lte(String leftColumn, String rightColumn) {
        return new Connection(leftColumn, "<=", rightColumn);
    }
}
