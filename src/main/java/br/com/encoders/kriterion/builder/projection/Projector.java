package br.com.encoders.kriterion.builder.projection;

public final class Projector {

    private Projector() {}

    public static PropertyProjection property(String propertyName) {
        return PropertyProjection.builder()
                .property(propertyName);
    }

    public static AggregatorProjection count(String propertyName) {
        return AggregatorProjection.builder()
                .aggregate(Aggregator.COUNT, propertyName);
    }

    public static AggregatorProjection sum(String propertyName) {
        return AggregatorProjection.builder()
                .aggregate(Aggregator.SUM, propertyName);
    }

    public static AggregatorProjection min(String propertyName) {
        return AggregatorProjection.builder()
                .aggregate(Aggregator.MIN, propertyName);
    }

    public static AggregatorProjection max(String propertyName) {
        return AggregatorProjection.builder()
                .aggregate(Aggregator.MAX, propertyName);
    }

    public static AggregatorProjection avg(String propertyName) {
        return AggregatorProjection.builder()
                .aggregate(Aggregator.AVG, propertyName);
    }

}
