# Kriterion

A simple and fluent SQL query builder for Java that allows you to construct SQL queries programmatically using a type-safe, builder pattern API.

## Features

- **Fluent API**: Chain methods to build queries in a readable way
- **Type-safe**: Compile-time safety for query construction
- **State Management**: Prevents duplicate clauses and invalid query structures
- **Aggregate Functions**: Support for COUNT, SUM, MIN, MAX, and AVG
- **Flexible Conditions**: Rich set of WHERE clause conditions with AND/OR support
- **Table Aliases**: Support for table and column aliases

## Quick Start

### Basic SELECT Query

```java
import br.com.encoders.kriterion.QueryOver;

var statement = QueryOver.builder()
    .selectAll()
    .from("Customers")
    .build();

System.out.println(statement.sql());
// Output: SELECT * FROM Customers
```

### SELECT Specific Columns

```java
import br.com.encoders.kriterion.QueryOver;
import br.com.encoders.kriterion.builder.projection.Projector;

var statement = QueryOver.builder()
    .select(
        Projector.property("firstName"),
        Projector.property("lastName"),
        Projector.property("age")
    )
    .from("Customers")
    .build();

System.out.println(statement.sql());
// Output: SELECT firstName, lastName, age FROM Customers
```

### SELECT with Aliases

```java
var statement = QueryOver.builder()
    .select(
        Projector.property("c.firstName").as("Name"),
        Projector.property("c.lastName").as("FamilyName"),
        Projector.property("c.age").as("CurrentAge")
    )
    .from("Customers", "c")
    .build();

System.out.println(statement.sql());
// Output: SELECT c.firstName AS Name, c.lastName AS FamilyName, c.age AS CurrentAge FROM Customers c
```

### Aggregate Functions

```java
// COUNT
var countQuery = QueryOver.builder()
    .select(Projector.count("c.age").as("CountAge"))
    .from("Customers", "c")
    .build();
// Output: SELECT COUNT(c.age) AS CountAge FROM Customers c

// SUM
var sumQuery = QueryOver.builder()
    .select(Projector.sum("c.age").as("SumAge"))
    .from("Customers", "c")
    .build();
// Output: SELECT SUM(c.age) AS SumAge FROM Customers c

// MIN
var minQuery = QueryOver.builder()
    .select(Projector.min("c.age").as("MinAge"))
    .from("Customers", "c")
    .build();
// Output: SELECT MIN(c.age) AS MinAge FROM Customers c

// MAX
var maxQuery = QueryOver.builder()
    .select(Projector.max("c.age").as("MaxAge"))
    .from("Customers", "c")
    .build();
// Output: SELECT MAX(c.age) AS MaxAge FROM Customers c

// AVG
var avgQuery = QueryOver.builder()
    .select(Projector.avg("c.age").as("AverageAge"))
    .from("Customers", "c")
    .build();
// Output: SELECT AVG(c.age) AS AverageAge FROM Customers c
```

## WHERE Clauses

### Basic WHERE Conditions

```java
import br.com.encoders.kriterion.builder.condition.Conditioner;

// Equality
var statement = QueryOver.builder()
        .selectAll()
        .from("Customers")
        .where(Conditioner.eq("age", 18))
        .build();
// Output: SELECT * FROM Customers WHERE age = 18

        // Not Equal
        var statement = QueryOver.builder()
                .selectAll()
                .from("Customers")
                .where(Conditioner.neq("status", "inactive"))
                .build();
// Output: SELECT * FROM Customers WHERE status <> 'inactive'
```

### Comparison Operators

```java
// Greater than
.where(Conditioner.gt("age", 18))
// Output: WHERE age > 18

// Less than
.where(Conditioner.lt("age", 65))
// Output: WHERE age < 65

// Greater than or equal
.where(Conditioner.gte("age", 18))
// Output: WHERE age >= 18

// Less than or equal
.where(Conditioner.lte("age", 65))
// Output: WHERE age <= 65
```

### Advanced WHERE Conditions

```java
// LIKE
.where(Conditioner.like("name", "%John%"))
// Output: WHERE name LIKE '%John%'

// IN
.where(Conditioner.in("status", "active", "pending", "approved"))
// Output: WHERE status IN ("active", "pending", "approved")

// BETWEEN
.where(Conditioner.between("age", 18, 65))
// Output: WHERE age BETWEEN 18 AND 65

// IS NULL
.where(Conditioner.isNull("deletedAt"))
// Output: WHERE deletedAt IS NULL

// IS NOT NULL
.where(Conditioner.isNotNull("email"))
// Output: WHERE email IS NOT NULL
```

### Composite Conditions (AND/OR)

```java
// Multiple conditions with AND
var statement = QueryOver.builder()
    .selectAll()
    .from("Customers")
    .where(
        Conditioner.eq("age", 18)
            .and("kind", "RED")
    )
    .build();
// Output: SELECT * FROM Customers WHERE age = 18 AND kind = 'RED'

// Chaining multiple AND conditions
var statement = QueryOver.builder()
    .selectAll()
    .from("Customers")
    .where(
        Conditioner.eq("age", 18)
            .and("status", "active")
            .and("verified", true)
    )
    .build();
// Output: SELECT * FROM Customers WHERE age = 18 AND status = 'active' AND verified = TRUE

// Using OR
var statement = QueryOver.builder()
    .selectAll()
    .from("Customers")
    .where(
        Conditioner.eq("status", "active")
            .or("status", "pending")
    )
    .build();
// Output: SELECT * FROM Customers WHERE status = 'active' OR status = 'pending'

// Complex conditions with nested AND/OR
var statement = QueryOver.builder()
    .selectAll()
    .from("Customers")
    .where(
        Conditioner.eq("age", 18)
            .and(Conditioner.eq("status", "active")
                .or("status", "pending"))
    )
    .build();
```

## API Reference

### QueryOver

The main entry point for building queries.

- `QueryOver.builder()` - Creates a new query builder instance
- `selectAll()` - Selects all columns (`SELECT *`)
- `select(Projection... projections)` - Selects specific columns or expressions
- `from(String table)` - Specifies the table name
- `from(String table, String alias)` - Specifies the table name with an alias
- `where(Condition condition)` - Adds a WHERE clause
- `build()` - Builds and returns a `QueryStatement`

### Projector

Factory class for creating projections (columns to select).

- `Projector.property(String propertyName)` - Creates a property projection
- `Projector.count(String propertyName)` - Creates a COUNT aggregation
- `Projector.sum(String propertyName)` - Creates a SUM aggregation
- `Projector.min(String propertyName)` - Creates a MIN aggregation
- `Projector.max(String propertyName)` - Creates a MAX aggregation
- `Projector.avg(String propertyName)` - Creates an AVG aggregation

**Projection Methods:**
- `.as(String alias)` - Adds an alias to the projection

### Conditioner

Factory class for creating WHERE conditions.

**Comparison Operators:**
- `Conditioner.eq(String column, Object value)` - Equality (`=`)
- `Conditioner.neq(String column, Object value)` - Not equal (`<>`)
- `Conditioner.gt(String column, Object value)` - Greater than (`>`)
- `Conditioner.lt(String column, Object value)` - Less than (`<`)
- `Conditioner.gte(String column, Object value)` - Greater than or equal (`>=`)
- `Conditioner.lte(String column, Object value)` - Less than or equal (`<=`)

**Advanced Operators:**
- `Conditioner.like(String column, String pattern)` - LIKE pattern matching
- `Conditioner.in(String column, Object... values)` - IN clause
- `Conditioner.between(String column, Object start, Object end)` - BETWEEN range
- `Conditioner.isNull(String column)` - IS NULL check
- `Conditioner.isNotNull(String column)` - IS NOT NULL check

**Composite Conditions:**
- `.and(String column, Object value)` - Adds an AND condition
- `.and(Condition condition)` - Adds an AND condition with another condition
- `.or(String column, Object value)` - Adds an OR condition
- `.or(Condition condition)` - Adds an OR condition with another condition

### QueryStatement

The result of building a query.

- `QueryStatement.sql()` - Returns the generated SQL string

## Value Formatting

The library automatically formats values according to their type:

- **Strings**: Wrapped in single quotes (`'value'`)
- **Numbers**: Used as-is (`42`, `3.14`)
- **Booleans**: Converted to SQL boolean (`TRUE`, `FALSE`)
- **Collections**: Formatted as IN clause values (`("value1", "value2")`)

## Error Handling

The builder prevents invalid query structures:

- **Duplicate Clauses**: Attempting to add the same clause twice (e.g., calling `select()` twice) will throw an `IllegalStateException`
- **State Validation**: The builder tracks which clauses have been composed to ensure query validity

## Examples

### Complete Example

```java
import br.com.encoders.kriterion.QueryOver;
import br.com.encoders.kriterion.builder.projection.Projector;
import br.com.encoders.kriterion.builder.condition.Conditioner;

var statement = QueryOver.builder()
    .select(
        Projector.property("c.id").as("CustomerId"),
        Projector.property("c.name").as("CustomerName"),
        Projector.avg("o.total").as("AverageOrderValue")
    )
    .from("Customers", "c")
    .where(
        Conditioner.gte("c.age", 18)
            .and("c.status", "active")
            .and(Conditioner.isNotNull("c.email"))
    )
    .build();

System.out.println(statement.sql());
```

## Building

This is a Maven project. To build:

```bash
mvn clean install
```

## License

See [LICENSE](LICENSE) file for details.
