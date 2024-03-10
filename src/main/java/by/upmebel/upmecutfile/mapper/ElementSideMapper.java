package by.upmebel.upmecutfile.mapper;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.web.dto.request.side.ElementSideRequest;
import by.upmebel.upmecutfile.web.dto.response.side.ElementSideInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = HoleMapper.class)
public interface ElementSideMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "holes", ignore = true)
    @Mapping(target = "element", ignore = true)
    ElementSide map(ElementSideRequest request);

    @Mapping(target = "holes", source = "holes" )
    ElementSideInfo map(ElementSide side);

    List<ElementSideInfo> map(List<ElementSide> sides);

}
