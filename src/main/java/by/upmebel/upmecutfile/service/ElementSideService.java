package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.side.ElementSideResponse;

public interface ElementSideService {

    ElementSideResponse getSideByIds(Long elementId, Long sideId);
}
