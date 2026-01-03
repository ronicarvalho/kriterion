package br.com.encoders.kriterion;

import br.com.encoders.kriterion.builder.condition.ClauseValue;
import br.com.encoders.kriterion.builder.condition.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OperatorTest {
    @Test
    void expression_equal_number_test() {
        var expression = Operator.EQUAL.expression("age", ClauseValue.of(18));
        assertEquals("age = 18", expression);
    }

    @Test
    void expression_equal_string_test() {
        var expression = Operator.EQUAL.expression("name", ClauseValue.of("John"));
        assertEquals("name = 'John'", expression);
    }

    @Test
    void expression_equal_boolean_test() {
        var expression = Operator.EQUAL.expression("active", ClauseValue.of(true));
        assertEquals("active = TRUE", expression);
    }

    @Test
    void expression_in_test() {
        var expression = Operator.IN.expression("city", ClauseValue.of("Salvador", "Curitiba", "Campinas"));
        assertEquals("city IN ('Salvador', 'Curitiba', 'Campinas')", expression);
    }

    @Test
    void expression_between_test() {
        var expression = Operator.BETWEEN.expression("price", ClauseValue.of(10, 100));
        assertEquals("price BETWEEN 10 AND 100", expression);
    }

    @Test
    void expression_is_not_null_test() {
        var expression = Operator.IS_NOT_NULL.expression("documentNumber", null);
        assertEquals("documentNumber IS NOT NULL", expression);
    }

    @Test
    void expression_is_null_test() {
        var expression = Operator.IS_NULL.expression("blocked", null);
        assertEquals("blocked IS NULL", expression);
    }

    @Test
    void expression_like_test() {
        var expression = Operator.LIKE.expression("name", ClauseValue.of("%John%"));
        assertEquals("name LIKE '%John%'", expression);
    }
}
