package by.upmebel.upmecutfile.service;

import by.upmebel.upmecutfile.web.dto.request.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;

public interface HoleService {
    HoleInfo createHole(Long elementId, Long sideId, CreateHoleRequest request);

    HoleInfo updateHole(Long elementId, Long sideId, Long holeId, UpdateHoleRequest request);


    void deleteById(Long elementId, Long sideId, Long holeId);


    HoleInfo getHoleByIds(Long elementId, Long sideId, Long holeId);
}
