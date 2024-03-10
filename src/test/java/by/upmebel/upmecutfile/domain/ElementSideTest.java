package by.upmebel.upmecutfile.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElementSideTest {

    @Test
    void shouldReturnClonedSide_whenClone_givenSide() {
        //given
        var element = new Element();
        var holes = List.of(
                new Hole(),
                new Hole()
        );
        var side = new ElementSide(11L, 44, 25, element, holes);
        var expected = new ElementSide(null, 44, 25, element, null);

        //when
        ElementSide actual = side.clone();

        //
        assertThat(actual).isEqualTo(expected);
    }
}