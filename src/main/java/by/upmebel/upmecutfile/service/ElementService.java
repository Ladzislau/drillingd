package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.element.ElementResponse;
import by.upmebel.upmecutfile.web.dto.element.ElementsPageResponse;
import org.springframework.data.domain.Pageable;

public interface ElementService {
    ElementResponse createElement(CreateElementRequest request);

    ElementResponse getElementById(Long elementId);

    ElementResponse updateElement(Long elementId, UpdateElementRequest request);

    ElementsPageResponse getElements(Pageable pageable);

    void deleteById(Long elementId);
}
