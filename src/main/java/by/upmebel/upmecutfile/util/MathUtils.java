package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.MathOperation;
import by.upmebel.upmecutfile.domain.MathOperator;
import by.upmebel.upmecutfile.exception.InvalidPatternException;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;


public class MathUtils {

    public static final String MATH_EXPRESSION_REGEX = "(\\s*(\\d+(\\.\\d+)?)\\s*)(\\s*[+\\-*/]\\s*(\\d+(\\.\\d+)?)\\s*)+";
    public static final String MATH_OPERATOR_REGEX = "\\s*[+\\-*/]\\s*";
    public static final String DECIMAL_NUMBER_REGEX = "\\s*\\d+(\\.\\d+)?\\s*";

    public static Queue<MathOperation> getOperations(String mathExpression) {
        if (!isMathExpression(mathExpression)) {
            throw new InvalidPatternException("Введенное выражение не соответствует паттерну");
        }

        Queue<MathOperation> operations = new ArrayDeque<>();

        String[] nums = mathExpression.split(MATH_OPERATOR_REGEX);
        String[] operators = mathExpression.split(DECIMAL_NUMBER_REGEX);

        for (int i = 0; i < operators.length; i++) {
            double operand = Double.parseDouble(nums[i]);
            String operatorAsString = operators[i];
            MathOperator operator = MathOperator.PLUS;

            if (!operatorAsString.isEmpty() || !operatorAsString.isBlank())
                operator = MathOperator.fromValue(operators[i]);

            operations.add(new MathOperation(operator, operand));
        }

        return operations;
    }

    public static double performOperation(MathOperator operator, double operand1, double operand2) {
        switch (operator) {
            case PLUS -> {
                return operand1 + operand2;
            }
            case MINUS -> {
                return operand1 - operand2;
            }
            case MULTIPLY -> {
                return operand1 * operand2;
            }
            case DIVIDE -> {
                return operand1 / operand2;
            }
            default -> throw new IllegalArgumentException("Неподдерживаемый оператор: " + operator);
        }
    }

    public static double calculateOperationsResult(Queue<MathOperation> operations) {
        if (operations == null || operations.isEmpty()) {
            return 0;
        }

        Stack<Double> operandsStack = new Stack<>();
        while (!operations.isEmpty()) {
            MathOperation operation = operations.poll();
            double operand = operation.getOperand();
            MathOperator operator = operation.getMathOperator();

            if (isHighPriorityOperation(operation)) {
                double previousOperand = operandsStack.pop();
                operand = performOperation(operator, previousOperand, operand);
            }

            if (operator.equals(MathOperator.MINUS)) {
                operand = operand * (-1);
            }

            operandsStack.push(operand);
        }

        double result = 0;
        while (!operandsStack.isEmpty()) {
            result += operandsStack.pop();
        }

        return result;
    }

    public static boolean isHighPriorityOperation(MathOperation operation) {
        return operation.getMathOperator() == MathOperator.MULTIPLY || operation.getMathOperator() == MathOperator.DIVIDE;
    }

    public static boolean isMathExpression(String input) {
        return Pattern.matches(MATH_EXPRESSION_REGEX, input);
    }
}
