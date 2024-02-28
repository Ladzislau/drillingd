package by.upmebel.upmecutfile.constant;

import lombok.Getter;

@Getter
public enum MathOperation {

    PLUS("+"),
    MINUS("-"),
    DIVIDE("/"),
    MULTIPLY("*");

    private final String value;

    MathOperation(String value) {
        this.value = value;
    }

    public static MathOperation fromValue(String value) {
        for (MathOperation operation : MathOperation.values()) {
            if (operation.value.equals(value)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
