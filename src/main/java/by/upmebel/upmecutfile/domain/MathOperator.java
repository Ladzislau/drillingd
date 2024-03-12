package by.upmebel.upmecutfile.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum MathOperator {

    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*");

    private static final Map<String, MathOperator> BY_VALUE = new HashMap<>();
    private final String value;

    MathOperator(String value) {
        this.value = value;
    }

    public static MathOperator fromValue(String value) {
        if (BY_VALUE.isEmpty()) {
            for (MathOperator operator : values()) {
                BY_VALUE.put(operator.value, operator);
            }
        }

        return BY_VALUE.get(value);
    }

}
