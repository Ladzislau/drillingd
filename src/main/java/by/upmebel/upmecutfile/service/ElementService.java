package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.request.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.response.element.ElementInfo;
import by.upmebel.upmecutfile.web.dto.response.element.ElementsPageResponse;
import org.springframework.data.domain.Pageable;

public interface ElementService {
    ElementInfo createElement(CreateElementRequest request);

    ElementInfo getElementById(Long elementId);

    ElementInfo updateElement(Long elementId, UpdateElementRequest request);

    ElementsPageResponse getElements(Pageable pageable);

    void deleteById(Long elementId);
}
