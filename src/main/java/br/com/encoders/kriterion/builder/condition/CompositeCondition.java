package br.com.encoders.kriterion.builder.condition;

import java.util.ArrayList;
import java.util.List;

// ref: https://www.w3schools.com/sql/sql_where.asp

public class CompositeCondition extends Condition {

    private final List<Condition> conditions = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();

    public CompositeCondition(Condition first) {
        this.conditions.add(first);
    }

    public CompositeCondition and(String column, Object value) {
        this.operators.add("AND");
        this.conditions.add(new Clause(column, Operator.EQUAL, ClauseValue.of(value)));
        return this;
    }

    public CompositeCondition and(Condition condition) {
        this.operators.add("AND");
        this.conditions.add(condition);
        return this;
    }

    public CompositeCondition or(String column, Object value) {
        this.operators.add("OR");
        this.conditions.add(new Clause(column, Operator.EQUAL, ClauseValue.of(value)));
        return this;
    }

    public CompositeCondition or(Condition condition) {
        this.operators.add("OR");
        this.conditions.add(condition);
        return this;
    }

    @Override
    public String render() {
        var expression = new StringBuilder();

        expression.append(this.conditions.get(0).render());

        for (int i = 0; i < this.operators.size(); i++) {
            expression.append(" ").append(this.operators.get(i)).append(" ");
            expression.append(this.conditions.get(i + 1).render());
        }

        return expression.toString();
    }
}
