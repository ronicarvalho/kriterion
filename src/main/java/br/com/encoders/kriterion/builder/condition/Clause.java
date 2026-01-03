package br.com.encoders.kriterion.builder.condition;

import java.util.Collection;
import java.util.stream.Collectors;

public class Clause extends Condition {

    private final String column;
    private final String operator;
    private final Object value;

    public Clause(String column, String operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String render() {
        if (value == null) {
            return column + " " + operator;
        }
        return column + " " + operator + " " + this.format(value);
    }

    private String format(Object value) {
        if (value instanceof String stringValue) return "'" + stringValue + "'";
        if (value instanceof Boolean booleanValue) return booleanValue ? "TRUE" : "FALSE";
        if (value instanceof Number numberValue) return numberValue.toString();
        if (value instanceof Collection) {
            Collection<?> collection = (Collection<?>) value;
            return collection.stream()
                    .map(val -> val instanceof String ? "\"" + val + "\"" : val.toString())
                    .collect(Collectors.joining(", ", "(", ")"));
        }
        return String.valueOf(value);
    }
}
