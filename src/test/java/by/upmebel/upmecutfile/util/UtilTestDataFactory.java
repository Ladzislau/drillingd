package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.MathOperation;
import by.upmebel.upmecutfile.domain.MathOperator;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class UtilTestDataFactory {

    public static Stream<Arguments> getArgumentsForGetOperationsTest() {
        String expression1 = "12 + 8.4 - 2";
        Queue<MathOperation> operationsExpr1 = new ArrayDeque<>(List.of(
                new MathOperation(MathOperator.PLUS, 12),
                new MathOperation(MathOperator.PLUS, 8.4),
                new MathOperation(MathOperator.MINUS, 2)
        ));

        String expression2 = "9 / 2 * 2";
        Queue<MathOperation> operationsExpr2 = new ArrayDeque<>(List.of(
                new MathOperation(MathOperator.PLUS, 9),
                new MathOperation(MathOperator.DIVIDE, 2),
                new MathOperation(MathOperator.MULTIPLY, 2)
        ));

        return Stream.of(
                Arguments.of(expression1, operationsExpr1),
                Arguments.of(expression2, operationsExpr2)
        );
    }

    public static Stream<Arguments> getArgumentsForPerformOperationTest() {
        return Stream.of(
                Arguments.of(MathOperator.PLUS, 5, 5, 10),
                Arguments.of(MathOperator.MINUS, 10, 8, 2),
                Arguments.of(MathOperator.MULTIPLY, 6, 3, 18),
                Arguments.of(MathOperator.DIVIDE, 7, 2, 3.5)
        );
    }

    public static Stream<Arguments> getArgumentsForCalculateOperationResultTest() {
        double expectedResult1 = 6 - 2 * 2.5 * 3 + 10;
        Queue<MathOperation> operations1 = new ArrayDeque<>(List.of(
                new MathOperation(MathOperator.PLUS, 6),
                new MathOperation(MathOperator.MINUS, 2),
                new MathOperation(MathOperator.MULTIPLY, 2.5),
                new MathOperation(MathOperator.MULTIPLY, 3),
                new MathOperation(MathOperator.PLUS, 10)
        ));

        double expectedResult2 = ((double) 4) / 3 + 10 * 2 - 5;
        Queue<MathOperation> operations2 = new ArrayDeque<>(List.of(
                new MathOperation(MathOperator.PLUS, 4),
                new MathOperation(MathOperator.DIVIDE, 3),
                new MathOperation(MathOperator.PLUS, 10),
                new MathOperation(MathOperator.MULTIPLY, 2),
                new MathOperation(MathOperator.MINUS, 5)
        ));

        return Stream.of(
                Arguments.of(operations1, expectedResult1),
                Arguments.of(operations2, expectedResult2)
        );
    }

    public static Stream<Arguments> getArgumentsForReplaceParametersWithValuesTest() {
        var side1 = new ElementSide(12, 5.6);
        double height1 = 8;
        String coordinateWithParams1 = "L/2 + B*4 - h +2";
        String expectedResult1 = "12.0/2 + 5.6*4 - 8.0 +2";

        var side2 = new ElementSide(12, 5.6);
        double height2 = 8;
        String coordinateWithParams2 = " L/2";
        String expectedResult2 = " 12.0/2";

        var side3 = new ElementSide(12, 5.6);
        double height3 = 8.5;
        String coordinateWithParams3 = "H";
        String expectedResult3 = "8.5";

        return Stream.of(
                Arguments.of(coordinateWithParams1, side1, height1, expectedResult1),
                Arguments.of(coordinateWithParams2, side2, height2, expectedResult2),
                Arguments.of(coordinateWithParams3, side3, height3, expectedResult3)
        );
    }

    public static Stream<Arguments> getArgumentsForGetMissingSideTest() {
        var expected1 = new ElementSide(40, 20);
        var firstSide1 = new ElementSide(40, 38);
        var secondSide1 = new ElementSide(38, 20);

        var expected2 = new ElementSide(53, 29);
        var firstSide2 = new ElementSide(47, 29);
        var secondSide2 = new ElementSide(53, 47);

        return Stream.of(
                Arguments.of(firstSide1, secondSide1, expected1),
                Arguments.of(firstSide2, secondSide2, expected2)
        );
    }

    public static Stream<Arguments> getInvalidArgumentsForGetMissingSideTest() {
        var firstSide1 = new ElementSide(43, 40);
        var secondSide1 = new ElementSide(50, 20);

        var firstSide2 = new ElementSide(-5, 17);
        var secondSide2 = new ElementSide(24, 17);

        var firstSide3 = new ElementSide(0, 10);
        var secondSide3 = new ElementSide(0, 0);

        return Stream.of(
                Arguments.of(firstSide1, secondSide1),
                Arguments.of(firstSide2, secondSide2),
                Arguments.of(firstSide3, secondSide3)
        );
    }

    public static Stream<Arguments> getArgumentsForGetHeightBySideTest() {
        var element1 = new Element();
        var sides1 = List.of(
                new ElementSide(44, 35),
                new ElementSide(60, 44),
                new ElementSide(60, 35)
        );
        element1.setSides(sides1);
        ElementSide side1 = sides1.getFirst();
        side1.setElement(element1);
        double expected1 = 60;

        var element2 = new Element();
        var sides2 = List.of(
                new ElementSide(38, 29),
                new ElementSide(29, 23),
                new ElementSide(38, 23)
        );
        element2.setSides(sides2);
        ElementSide side2 = sides2.getFirst();
        side2.setElement(element2);
        double expected2 = 23;

        return Stream.of(
                Arguments.of(side1, expected1),
                Arguments.of(side2, expected2)
        );
    }
}
