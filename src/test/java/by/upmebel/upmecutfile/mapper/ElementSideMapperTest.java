package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.web.dto.request.side.ElementSideRequest;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ElementSideMapperImpl.class,
        HoleMapperImpl.class
})
class ElementSideMapperTest {

    @Autowired
    ElementSideMapper elementSideMapper;

    @Test
    void shouldReturnElementSideEntity_whenMap_givenElementSideRequest() {
        //given
        double length = 35;
        double breadth = 27;
        var elementSideRequest = new ElementSideRequest(length, breadth);
        ElementSide expected = new ElementSide(null, length, breadth, null, null);

        //when
        ElementSide actual = elementSideMapper.map(elementSideRequest);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnElementSideInfo_whenMap_givenElementSideEntity() {
        //given
        long id = 1L;
        double length = 35;
        double breadth = 27;
        var holeInfos = List.of(
                new HoleInfo(1L, 10d, 10d, 10,
                10, 10d, 10d),
                new HoleInfo(2L, 20d, 20d, 20,
                        20, 20d, 20d)
        );
        var expectedHoles = List.of(
                new Hole(1L, 10, 10, 10,
                        10, 10, 10, null),
                new Hole(2L, 20, 20, 20,
                        20, 20, 20, null)
        );
        var side = new ElementSide(id, length, breadth, null, expectedHoles);
        var expected = new ElementSideInfo(id, length, breadth, holeInfos);

        //when
        ElementSideInfo actual = elementSideMapper.map(side);

        //then
        assertThat(actual).isEqualTo(expected);
    }



}