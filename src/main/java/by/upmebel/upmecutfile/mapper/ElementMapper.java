package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.web.dto.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.element.ElementResponse;
import by.upmebel.upmecutfile.web.dto.element.ElementsPageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring", uses = ElementSideMapper.class)
public interface ElementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Element map(CreateElementRequest request);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Element map(UpdateElementRequest request);

    ElementResponse map(Element element);

    List<ElementResponse> map(List<Element> elements);

    default ElementsPageResponse map(Page<Element> elementsPage) {
        List<Element> elements = elementsPage.getContent();
        Pageable pageable = elementsPage.getPageable();
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int totalPages = elementsPage.getTotalPages();
        long totalElements = elementsPage.getTotalElements();

        List<ElementResponse> elementResponses = map(elements);

        return new ElementsPageResponse(pageNumber, pageSize, totalPages, totalElements, elementResponses);
    }

}
