package br.com.encoders.kriterion.builder.condition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum Operator {
    EQUAL("=") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("EQUAL requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    NOT_EQUAL("<>") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("NOT EQUAL requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    GREATER_THEN(">") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("GREATER THEN requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    GREATER_OR_EQUAL(">=") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("GREATER OR EQUAL requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    LESS_THEN("<") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("LESS THEN requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    LESS_OR_EQUAL("<=") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("LESS OR EQUAL requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    LIKE("LIKE") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("LIKE requires 1 value");
            }
            return fieldName + " " + this.operator + " " + this.format(clause.get(0));
        }
    },
    IN("IN") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 1) {
                throw new IllegalArgumentException("NOT EQUAL requires 1 value");
            }
            String values = clause.get().stream()
                    .map(this::format)
                    .collect(Collectors.joining(", ", "(", ")"));
            return fieldName + " " + this.operator + " " + values;
        }
    },
    BETWEEN("BETWEEN") {
        public String expression(String fieldName, ClauseValue clause) {
            if (clause.size() < 2) {
                throw new IllegalArgumentException("BETWEEN requires 2 values");
            }
            return fieldName + " " + this.operator + " " +
                    this.format(clause.get(0)) + " AND " + this.format(clause.get(1));
        }
    },
    IS_NULL("IS NULL") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            return fieldName + " " + this.operator;
        }
    },
    IS_NOT_NULL("IS NOT NULL") {
        @Override
        public String expression(String fieldName, ClauseValue clause) {
            return fieldName + " " + this.operator;
        }
    };

    String operator;

    Operator(String operator) {
        this.operator = operator;
    }

    public abstract String expression(String fieldName, ClauseValue clause);

    String format(Object value) {
        if (value == null) {
            return "NULL";
        }
        if (value instanceof String stringValue) {
            return "'" + stringValue + "'";
        }
        if (value instanceof Boolean booleanValue) {
            return booleanValue ? "TRUE" : "FALSE";
        }
        if (value instanceof Number numberValue) {
            return numberValue.toString();
        }
        if (value instanceof Collection<?> collectionValue) {
            return collectionValue.stream()
                    .map(this::format)
                    .collect(Collectors.joining(", ", "(", ")"));
        }
        return String.valueOf(value);
    }
}