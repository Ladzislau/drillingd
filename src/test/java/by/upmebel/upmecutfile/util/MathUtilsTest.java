package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.MathOperation;
import by.upmebel.upmecutfile.domain.MathOperator;
import by.upmebel.upmecutfile.exception.InvalidPatternException;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MathUtilsTest {


    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForGetOperationsTest")
    void shouldReturnOperationQueue_whenGetOperations_givenCorrectExpression(
            String mathExpression, Queue<MathOperation> expected) {
        //given (parametrized test)

        //when
        Queue<MathOperation> actual = MathUtils.getOperations(mathExpression);

        //then
        assertAll(
                () -> assertThat(actual).hasSameSizeAs(expected),
                () -> assertThat(actual).hasSameElementsAs(expected)
        );
    }

    @Test
    void shouldThrowInvalidPatternException_whenGetOperations_givenInvalidExpression() {
        //given
        String mathExpression = "invalid pattern";

        //when

        //then
        assertThrows(
                InvalidPatternException.class,
                () -> MathUtils.getOperations(mathExpression)
        );
    }

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForPerformOperationTest")
    void shouldReturnExpressionResult_whenPerformOperation_givenValidArguments(
            MathOperator operator, double num1, double num2, double expected){
        //given (parametrized test)

        //when
        double actual = MathUtils.performOperation(operator, num1, num2);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForCalculateOperationResultTest")
    void shouldReturnOperationsResult_whenCalculateOperationResult_givenOperations(
            Queue<MathOperation> operations, double expected){
        //given (parametrized test)

        //when
        double actual = MathUtils.calculateOperationsResult(operations);

        //then
        assertThat(actual).isEqualTo(expected, Offset.offset(0.0001));
    }


}