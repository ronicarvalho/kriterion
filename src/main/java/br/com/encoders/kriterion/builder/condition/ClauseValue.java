package br.com.encoders.kriterion.builder.condition;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ClauseValue {
    private List<Object> values;

    private ClauseValue(Object... values) {
        this.values = new ArrayList<>();
        Arrays.stream(values)
                .forEach(item -> this.values.add(item));
    }

    public static ClauseValue of(Object... values) {
        return new ClauseValue(values);
    }

    public int size() {
        return this.values.size();
    }

    public Object get(int index) {
        return this.values.get(index);
    }

    public List<Object> get() {
        return this.values;
    }
}
