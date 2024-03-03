package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.ElementSide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinateUtilsTest {

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForReplaceParametersWithValuesTest")
    void shouldReturnModifiedString_whenReplaceParametersWithValues_givenCoordinateMathExpression(
            String coordinate, ElementSide side, double height, String expected) {
        //given (parametrized test)

        //when
        String actual = CoordinateUtils.replaceParametersWithValues(coordinate, side, height);

        //then
        assertThat(actual).isEqualTo(expected);
    }


}