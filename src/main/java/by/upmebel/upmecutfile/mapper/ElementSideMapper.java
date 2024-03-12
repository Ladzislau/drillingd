package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.web.dto.side.ElementSideResponse;
import by.upmebel.upmecutfile.web.dto.side.ElementSideRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = HoleMapper.class)
public interface ElementSideMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "holes", ignore = true)
    @Mapping(target = "element", ignore = true)
    ElementSide map(ElementSideRequest request);

    @Mapping(target = "holes", source = "holes")
    ElementSideResponse map(ElementSide side);

    List<ElementSideResponse> map(List<ElementSide> sides);

}
