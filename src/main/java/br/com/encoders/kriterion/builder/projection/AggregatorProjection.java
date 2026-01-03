package br.com.encoders.kriterion.builder.projection;

public class AggregatorProjection extends Projection {

    private Aggregator aggregator;
    private String propertyName;
    private String alias;

    private AggregatorProjection() {}

    public static AggregatorProjection builder() {
        return new AggregatorProjection();
    }

    public AggregatorProjection aggregate(Aggregator aggregator, String propertyName) {
        this.aggregator = aggregator;
        this.propertyName = propertyName;
        return this;
    }

    public AggregatorProjection as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String render() {
        return this.aggregator.aggregate(this.propertyName, this.alias);
    }
}
