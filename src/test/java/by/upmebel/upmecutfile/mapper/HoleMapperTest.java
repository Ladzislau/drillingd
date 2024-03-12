package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.web.dto.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.HoleResponse;
import by.upmebel.upmecutfile.web.dto.hole.UpdateHoleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HoleMapperImpl.class)
class HoleMapperTest {

    @Autowired
    HoleMapper holeMapper;

    @Test
    void shouldReturnHoleEntity_whenMap_givenCreateHoleRequest() {
        //given
        double diameter = 10;
        double depth = 8;
        int drillingEntrySpeed = 15;
        int drillingExitSpeed = 18;
        String coordinateByLength = "L/2";
        String coordinateByBreadth = "H + 15";
        var createHoleRequest = new CreateHoleRequest(diameter, depth, drillingEntrySpeed,
                drillingExitSpeed, coordinateByLength, coordinateByBreadth);
        Hole expected = new Hole(null, diameter, depth, drillingEntrySpeed,
                drillingExitSpeed, 0, 0, null);

        //when
        Hole actual = holeMapper.map(createHoleRequest);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnHoleEntity_whenMap_givenUpdateHoleRequest() {
        //given
        double diameter = 10;
        double depth = 8;
        int drillingEntrySpeed = 15;
        int drillingExitSpeed = 18;
        String coordinateByLength = "L/2";
        String coordinateByBreadth = "H + 15";
        var updateHoleRequest = new UpdateHoleRequest(diameter, depth, drillingEntrySpeed,
                drillingExitSpeed, coordinateByLength, coordinateByBreadth);
        Hole expected = new Hole(null, diameter, depth, drillingEntrySpeed,
                drillingExitSpeed, 0, 0, null);

        //when
        Hole actual = holeMapper.map(updateHoleRequest);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnHoleInfo_whenMap_givenHoleEntity() {
        //given
        long id = 11L;
        double diameter = 10;
        double depth = 8;
        int drillingEntrySpeed = 15;
        int drillingExitSpeed = 18;
        double coordinateByLength = 14L;
        double coordinateByBreadth = 12.5;
        Hole hole = new Hole(id, diameter, depth, drillingEntrySpeed, drillingExitSpeed,
                coordinateByLength, coordinateByBreadth, null);
        HoleResponse expected = new HoleResponse(id, diameter, depth, drillingEntrySpeed,
                drillingExitSpeed, coordinateByLength, coordinateByBreadth);

        //when
        HoleResponse actual = holeMapper.map(hole);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnHoleInfoList_whenMap_givenHoleEntityList() {
        //given
        long id = 11L;
        double diameter = 10;
        double depth = 8;
        int drillingEntrySpeed = 15;
        int drillingExitSpeed = 18;
        double coordinateByLength = 14L;
        double coordinateByBreadth = 12.5;
        var holeList = List.of(
                new Hole(1L, 1, 1, 1, 1,
                        1, 1, null),
                new Hole(id, diameter, depth, drillingEntrySpeed, drillingExitSpeed,
                        coordinateByLength, coordinateByBreadth, null)
        );
        var expected = List.of(
                new HoleResponse(1L, 1d, 1d, 1, 1,
                        1d, 1d),
                new HoleResponse(id, diameter, depth, drillingEntrySpeed, drillingExitSpeed,
                        coordinateByLength, coordinateByBreadth)
        );

        //when
        List<HoleResponse> actual = holeMapper.map(holeList);

        //then
        assertAll(
                () -> assertThat(actual).hasSameSizeAs(expected),
                () -> assertThat(actual).hasSameElementsAs(expected)
        );
    }
}