package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;

public interface ElementSideService {

    ElementSideInfo getSideByIds(Long elementId, Long sideId);
}
