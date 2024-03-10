package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.web.dto.request.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.side.ElementSideRequest;
import by.upmebel.upmecutfile.web.dto.response.element.ElementInfo;
import by.upmebel.upmecutfile.web.dto.response.element.ElementsPageResponse;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ElementMapperImpl.class,
        ElementSideMapperImpl.class,
        HoleMapperImpl.class
})
class ElementMapperTest {

    @Autowired
    ElementMapper elementMapper;

    @Test
    void shouldReturnElementEntity_whenMap_givenCreateElementRequest(){
        //given
        String elementName = "eto_element";
        var elementSideRequests = List.of(
                new ElementSideRequest(22.5, 13d),
                new ElementSideRequest(30d, 20d)
        );
        var expectedSides = List.of(
                new ElementSide(22.5, 13d),
                new ElementSide(30d, 20d)
        );
        var createElementRequest = new CreateElementRequest(elementName, elementSideRequests);
        Element expected = new Element(null, elementName, expectedSides);

        //when
        Element actual = elementMapper.map(createElementRequest);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnElementEntity_whenMap_givenUpdateElementRequest(){
        //given
        String elementName = "eto_updated_element";
        var elementSideRequests = List.of(
                new ElementSideRequest(22.5, 13d),
                new ElementSideRequest(30d, 20d)
        );
        var expectedSides = List.of(
                new ElementSide(22.5, 13d),
                new ElementSide(30d, 20d)
        );
        var updateElementRequest = new UpdateElementRequest(elementName, elementSideRequests);
        Element expected = new Element(null, elementName, expectedSides);

        //when
        Element actual = elementMapper.map(updateElementRequest);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnElementInfo_whenMap_givenElementEntity(){
        //given
        Date createdAt = new Date();
        String elementName = "eto_element";
        var holes = List.of(
                new Hole(1L, 10, 10, 10,
                        10, 10, 10, null),
                new Hole(2L, 20, 20, 20,
                        20, 20, 20, null)
        );
        var sides = List.of(
                new ElementSide(1L, 30, 20, null, holes),
                new ElementSide(2L, 20, 18, null, null),
                new ElementSide(3L, 30, 18, null, null)
        );
        var expectedHoleInfos = List.of(
                new HoleInfo(1L, 10d, 10d, 10,
                        10, 10d, 10d),
                new HoleInfo(2L, 20d, 20d, 20,
                        20, 20d, 20d)
        );
        var expectedSideInfos = List.of(
                new ElementSideInfo(1L, 30, 20, expectedHoleInfos),
                new ElementSideInfo(2L, 20, 18, null),
                new ElementSideInfo(3L, 30, 18,  null)
        );
        var element = new Element(1L, elementName, createdAt, sides);
        ElementInfo expected = new ElementInfo(1L, elementName, createdAt, expectedSideInfos);

        //when
        ElementInfo actual = elementMapper.map(element);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldReturnElementsPageResponse_whenMap_givenElementPage(){
        //given
        int pageNumber = 0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Date createdAt1 = new Date(231029302);
        Date createdAt2 = new Date(509301233);
        Date createdAt3 = new Date(809301237);
        var elements = List.of(
                new Element(1L, "eto_elem1", createdAt1, null),
                new Element(2L, "eto_elem2", createdAt2, null),
                new Element(3L, "eto_elem3", createdAt3, null)
        );
        var elementInfos = List.of(
                new ElementInfo(1L, "eto_elem1", createdAt1, null),
                new ElementInfo(2L, "eto_elem2", createdAt2, null),
                new ElementInfo(3L, "eto_elem3", createdAt3, null)
        );
        Page<Element> elementsPage = new PageImpl<>(elements, pageable, elements.size());
        ElementsPageResponse expected = new ElementsPageResponse(pageNumber, pageSize,
                elementsPage.getTotalPages(), elementsPage.getTotalElements(), elementInfos);

        //when
        ElementsPageResponse actual = elementMapper.map(elementsPage);

        //then
        assertThat(actual).isEqualTo(expected);
    }


}