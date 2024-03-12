package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.web.dto.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.HoleResponse;
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

    HoleResponse map(Hole hole);

    List<HoleResponse> map(List<Hole> holes);
}
