package br.com.encoders.kriterion.builder.condition;

public class Clause extends Condition {

    private final String column;
    private final Operator operator;
    private final ClauseValue value;

    public Clause(String column, Operator operator, ClauseValue value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public String render() {
        if (this.value == null) {
            return this.operator.expression(this.column, null);
        }
        return this.operator.expression(this.column, this.value);
    }
}
