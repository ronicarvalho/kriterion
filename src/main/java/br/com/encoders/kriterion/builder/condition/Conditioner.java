package br.com.encoders.kriterion.builder.condition;

import java.util.Arrays;

public class Conditioner {

    public static CompositeCondition eq(String column, Object value) {
        return new CompositeCondition(new Clause(column, "=", value));
    }

    public static CompositeCondition gt(String column, Object value) {
        return new CompositeCondition(new Clause(column, ">", value));
    }

    public static CompositeCondition lt(String column, Object value) {
        return new CompositeCondition(new Clause(column, "<", value));
    }

    public static CompositeCondition gte(String column, Object value) {
        return new CompositeCondition(new Clause(column, ">=", value));
    }

    public static CompositeCondition lte(String column, Object value) {
        return new CompositeCondition(new Clause(column, "<=", value));
    }

    public static CompositeCondition neq(String column, Object value) {
        return new CompositeCondition(new Clause(column, "<>", value));
    }

    public static CompositeCondition like(String column, String pattern) {
        return new CompositeCondition(new Clause(column, "LIKE", pattern));
    }

    public static CompositeCondition in(String column, Object... values) {
        return new CompositeCondition(new Clause(column, "IN", Arrays.asList(values)));
    }

    public static CompositeCondition between(String column, Object start, Object end) {
        var value = start + " AND " + end;
        return new CompositeCondition(new Clause(column, "BETWEEN", value));
    }

    public static CompositeCondition isNull(String column) {
        return new CompositeCondition(new Clause(column, "IS NULL", null));
    }

    public static CompositeCondition isNotNull(String column) {
        return new CompositeCondition(new Clause(column, "IS NOT NULL", null));
    }
}
