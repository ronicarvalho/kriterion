package br.com.encoders.kriterion.builder.condition;

public class Conditioner {

    public static CompositeCondition eq(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.EQUAL, ClauseValue.of(value)));
    }

    public static CompositeCondition gt(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.GREATER_THEN, ClauseValue.of(value)));
    }

    public static CompositeCondition lt(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.LESS_THEN, ClauseValue.of(value)));
    }

    public static CompositeCondition gte(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.GREATER_OR_EQUAL, ClauseValue.of(value)));
    }

    public static CompositeCondition lte(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.LESS_OR_EQUAL, ClauseValue.of(value)));
    }

    public static CompositeCondition neq(String column, Object value) {
        return new CompositeCondition(new Clause(column, Operator.NOT_EQUAL, ClauseValue.of(value)));
    }

    public static CompositeCondition like(String column, String pattern) {
        return new CompositeCondition(new Clause(column, Operator.LIKE, ClauseValue.of(pattern)));
    }

    public static CompositeCondition in(String column, Object... values) {
        return new CompositeCondition(new Clause(column, Operator.IN, ClauseValue.of(values)));
    }

    public static CompositeCondition between(String column, Object start, Object end) {
        return new CompositeCondition(new Clause(column, Operator.BETWEEN, ClauseValue.of(start, end)));
    }

    public static CompositeCondition isNull(String column) {
        return new CompositeCondition(new Clause(column, Operator.IS_NULL, null));
    }

    public static CompositeCondition isNotNull(String column) {
        return new CompositeCondition(new Clause(column, Operator.IS_NOT_NULL, null));
    }
}
