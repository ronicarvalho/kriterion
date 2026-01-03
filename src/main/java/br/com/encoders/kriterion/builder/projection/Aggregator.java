package br.com.encoders.kriterion.builder.projection;

public enum Aggregator {
    MIN("MIN"),
    MAX("MAX"),
    SUM("SUM"),
    AVG("AVG"),
    COUNT("COUNT");

    private String function;

    Aggregator(String function) {
        this.function = function;
    }

    public String aggregate(String fieldName, String alias) {
        return alias.isEmpty()
                ? this.function + "(" + fieldName + ")"
                : this.function + "(" + fieldName + ") AS " + alias;
    }
}
