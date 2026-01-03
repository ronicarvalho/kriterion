package br.com.encoders.kriterion.builder.projection;

public class PropertyProjection extends Projection {

    private String propertyName;
    private String alias;

    private PropertyProjection() {}

    public static PropertyProjection builder() {
        return new PropertyProjection();
    }

    public PropertyProjection property(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public PropertyProjection as(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public String render() {
        return this.alias == null
                ? this.propertyName
                : this.propertyName + " AS " + this.alias;
    }
}
