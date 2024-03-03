package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ElementSideUtilsTest {

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForGetMissingSideTest")
    void shouldReturnFurnitureComponentSide_whenGetMissingSide_givenSides(
            ElementSide side1, ElementSide side2, ElementSide expected) {
        //given (parametrized test)

        //when
        var actual = ElementSideUtils.getMissingSide(side1, side2);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getInvalidArgumentsForGetMissingSideTest")
    void shouldReturnNull_whenGetMissingSide_givenInvalidSides(
            ElementSide side1, ElementSide side2) {
        //given (parametrized test)

        //when
        var actual = ElementSideUtils.getMissingSide(side1, side2);

        //then
        assertThat(actual).isNull();
    }

    @ParameterizedTest
    @MethodSource("by.upmebel.upmecutfile.util.UtilTestDataFactory#getArgumentsForGetHeightBySideTest")
    void shouldReturnElementHeight_whenGetHeightBySide_givenSide(
            ElementSide side, double expected) {
        //given (parametrized test)

        //when
        double actual = ElementSideUtils.getHeightBySide(side);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnElementHeight_whenGetHeightBySide_givenInvalidSideWithInvalidElement() {
        //given
        var invalidElement = new Element();
        var sides = List.of(
                new ElementSide(12, 30),
                new ElementSide(44, 50)
        );
        invalidElement.setSides(sides);
        ElementSide sideWithInvalidElement = sides.getFirst();
        sideWithInvalidElement.setElement(invalidElement);

        //when
        double actual = ElementSideUtils.getHeightBySide(sideWithInvalidElement);

        //then
        assertThat(actual).isEqualTo(-1);
    }

    @Test
    void shouldReturnElementHeight_whenGetHeightBySide_givenInvalidSideWithNullElement() {
        //given
        var side = new ElementSide(44, 35);

        //when
        double actual = ElementSideUtils.getHeightBySide(side);

        //then
        assertThat(actual).isEqualTo(-1);
    }

    @Test
    void shouldReturnClonedSides_whenCloneSides_givenSides() {
        //given
        var element = new Element();
        var holes = List.of(
                new Hole(),
                new Hole()
        );
        var sides = List.of(
                new ElementSide(12L, 20, 13, element, holes),
                new ElementSide(13L, 28, 20, element, null),
                new ElementSide(14L, 28, 13, element, holes)
        );
        var expected = List.of(
                new ElementSide(null, 20, 13, element, null),
                new ElementSide(null, 28, 20, element, null),
                new ElementSide(null, 28, 13, element, null)
        );

        //when
        List<ElementSide> actual = ElementSideUtils.cloneSides(sides);

        //then
        assertAll(
                () -> assertThat(actual).hasSameSizeAs(expected),
                () -> assertThat(actual).hasSameElementsAs(expected)
        );
    }


}