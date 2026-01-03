package br.com.encoders.kriterion;

import br.com.encoders.kriterion.builder.condition.Conditioner;
import br.com.encoders.kriterion.builder.projection.Projector;
import br.com.encoders.kriterion.builder.relation.Connections;
import br.com.encoders.kriterion.builder.relation.Relations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryOverRelationsTest {
    @Test
    void statement_result_select_all_inner_and_left_query() {
        var statement =  QueryOver.builder()
                .selectAll()
                .from("Customers", "c")
                .join(
                        Relations
                                .inner("Products", "p")
                                .on(Connections.eq("c.Id", "p.CustomerId")),
                        Relations
                                .left("Orders", "o")
                                .on(Connections.eq("c.Id", "o.CustomerId"))
                )
                .build();

        assertEquals("SELECT * FROM Customers c INNER JOIN Products p ON c.Id = p.CustomerId LEFT JOIN Orders o ON c.Id = o.CustomerId", statement.sql());
    }

    @Test
    void statement_result_select_all_inner_and_left_and_left_query() {
        var statement = QueryOver.builder()
                .select(
                        Projector.property("c.Name"),
                        Projector.property("p.ProductName"),
                        Projector.property("o.OrderDate"),
                        Projector.property("s.ShipDate")
                )
                .from("Customers", "c")
                .join(
                        Relations.inner("Products", "p")
                                .on(Connections.eq("c.Id", "p.CustomerId")),
                        Relations.left("Orders", "o")
                                .on(Connections.eq("c.Id", "o.CustomerId")),
                        Relations.left("Shipments", "s")
                                .on(Connections.eq("o.Id", "s.OrderId"))
                )
                .where(
                        Conditioner.eq("c.active", true)
                )
                .build();

        assertEquals("SELECT c.Name, p.ProductName, o.OrderDate, s.ShipDate FROM Customers c INNER JOIN Products p ON c.Id = p.CustomerId LEFT JOIN Orders o ON c.Id = o.CustomerId LEFT JOIN Shipments s ON o.Id = s.OrderId WHERE c.active = TRUE", statement.sql());
    }
}
