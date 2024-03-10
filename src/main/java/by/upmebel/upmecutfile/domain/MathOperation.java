package by.upmebel.upmecutfile.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class MathOperation {

    private MathOperator mathOperator;
    private double operand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathOperation operation = (MathOperation) o;
        return Double.compare(operand, operation.operand) == 0 && mathOperator == operation.mathOperator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathOperator, operand);
    }
}
