package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.HoleResponse;

public interface HoleService {
    HoleResponse createHole(Long elementId, Long sideId, CreateHoleRequest request);

    HoleResponse updateHole(Long elementId, Long sideId, Long holeId, UpdateHoleRequest request);


    void deleteById(Long elementId, Long sideId, Long holeId);


    HoleResponse getHoleByIds(Long elementId, Long sideId, Long holeId);
}
