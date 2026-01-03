package br.com.encoders.kriterion;

import br.com.encoders.kriterion.builder.condition.Conditioner;
import br.com.encoders.kriterion.builder.projection.Projector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryOverSingleTableTest {
    @Test
    void statement_result_select_all_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .build();

        assertEquals("SELECT * FROM Customers", statement.sql());
    }

    @Test
    void statement_result_select_all_with_alias_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers", "c")
                .build();

        assertEquals("SELECT * FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_fields_query() {
        var statement = QueryOver.builder()
                .select(
                        Projector.property("firstName"),
                        Projector.property("lastName"),
                        Projector.property("age")
                )
                .from("Customers")
                .build();

        assertEquals("SELECT firstName, lastName, age FROM Customers", statement.sql());
    }

    @Test
    void statement_result_select_fields_with_alias_query() {
        var statement = QueryOver.builder()
                .select(
                        Projector.property("c.firstName").as("Name"),
                        Projector.property("c.lastName").as("FamilyName"),
                        Projector.property("c.age").as("CurrentAge")
                )
                .from("Customers", "c")
                .build();

        assertEquals("SELECT c.firstName AS Name, c.lastName AS FamilyName, c.age AS CurrentAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_max_value_query() {
        var statement = QueryOver.builder()
                .select(Projector.max("c.age").as("MaxAge"))
                .from("Customers", "c")
                .join()
                .build();

        assertEquals("SELECT MAX(c.age) AS MaxAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_min_value_query() {
        var statement = QueryOver.builder()
                .select(Projector.min("c.age").as("MinAge"))
                .from("Customers", "c")
                .join()
                .build();

        assertEquals("SELECT MIN(c.age) AS MinAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_avg_value_query() {
        var statement = QueryOver.builder()
                .select(Projector.avg("c.age").as("AverageAge"))
                .from("Customers", "c")
                .join()
                .build();

        assertEquals("SELECT AVG(c.age) AS AverageAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_sum_value_query() {
        var statement = QueryOver.builder()
                .select(Projector.sum("c.age").as("SumAge"))
                .from("Customers", "c")
                .join()
                .build();

        assertEquals("SELECT SUM(c.age) AS SumAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_count_value_query() {
        var statement = QueryOver.builder()
                .select(Projector.count("c.age").as("CountAge"))
                .from("Customers", "c")
                .join()
                .build();

        assertEquals("SELECT COUNT(c.age) AS CountAge FROM Customers c", statement.sql());
    }

    @Test
    void statement_result_select_all_where_eq_value_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(
                        Conditioner.eq("age", 18)
                )
                .build();

        assertEquals("SELECT * FROM Customers WHERE age = 18", statement.sql());
    }

    @Test
    void statement_result_select_all_where_neq_value_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(
                        Conditioner.neq("age", 18)
                )
                .build();

        assertEquals("SELECT * FROM Customers WHERE age <> 18", statement.sql());
    }

    @Test
    void statement_result_select_all_where_eq_value_and_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(
                        Conditioner.eq("age", 18)
                                .and("kind", "RED")
                )
                .build();

        assertEquals("SELECT * FROM Customers WHERE age = 18 AND kind = 'RED'", statement.sql());
    }

    @Test
    void statement_result_select_all_where_eq_value_or_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(
                        Conditioner.eq("age", 18)
                                .or("age", 25)
                )
                .build();

        assertEquals("SELECT * FROM Customers WHERE age = 18 OR age = 25", statement.sql());
    }

    @Test
    void statement_result_select_all_where_eq_value_or_between_query() {
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(
                        Conditioner.eq("age", 18)
                                .or(Conditioner.between("rank", 10, 20))
                )
                .build();

        assertEquals("SELECT * FROM Customers WHERE age = 18 OR rank BETWEEN 10 AND 20", statement.sql());
    }
}
