package br.com.encoders.kriterion.builder.relation;

import br.com.encoders.kriterion.builder.renderer.QueryRender;

public record Connection(String leftColumn, String operator, String rightColumn) implements QueryRender {
    @Override
    public String render() {
        return leftColumn + " " + operator + " " + rightColumn;
    }
}
