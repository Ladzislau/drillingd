package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.web.dto.request.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coordinateByLength", ignore = true)
    @Mapping(target = "coordinateByBreadth", ignore = true)
    @Mapping(target = "elementSide", ignore = true)
    Hole map(CreateHoleRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "coordinateByLength", ignore = true)
    @Mapping(target = "coordinateByBreadth", ignore = true)
    @Mapping(target = "elementSide", ignore = true)
    Hole map(UpdateHoleRequest request);

    HoleInfo map(Hole hole);

    List<HoleInfo> map(List<Hole> holes);
}
