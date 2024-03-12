package by.upmebel.upmecutfile.service.impl;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.exception.SideNotFoundException;
import by.upmebel.upmecutfile.mapper.ElementSideMapper;
import by.upmebel.upmecutfile.repository.ElementSideRepository;
import by.upmebel.upmecutfile.service.ElementSideService;
import by.upmebel.upmecutfile.web.dto.side.ElementSideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElementSideServiceImpl implements ElementSideService {

    private final ElementSideRepository elementSideRepository;
    private final ElementSideMapper elementSideMapper;

    @Override
    public ElementSideResponse getSideByIds(Long elementId, Long sideId) {
        ElementSide side = elementSideRepository.findBySideIdAndElementIdWithHoles(elementId, sideId)
                .orElseThrow(() -> new SideNotFoundException("Сторона детали не найдена. Проверьте правильность id детали и стороны"));

        return elementSideMapper.map(side);
    }
}
