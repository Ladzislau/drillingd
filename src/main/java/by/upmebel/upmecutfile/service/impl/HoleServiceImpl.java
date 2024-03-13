package by.upmebel.upmecutfile.service.impl;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.domain.MathOperation;
import by.upmebel.upmecutfile.exception.HoleNotFoundException;
import by.upmebel.upmecutfile.exception.SideNotFoundException;
import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.repository.ElementSideRepository;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.util.CoordinateUtils;
import by.upmebel.upmecutfile.util.ElementSideUtils;
import by.upmebel.upmecutfile.util.MathUtils;
import by.upmebel.upmecutfile.validation.validator.HoleValidator;
import by.upmebel.upmecutfile.web.dto.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.hole.HoleResponse;
import by.upmebel.upmecutfile.web.dto.hole.UpdateHoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Queue;

@Service
@RequiredArgsConstructor
public class HoleServiceImpl implements HoleService {

    private final HoleRepository holeRepository;
    private final ElementSideRepository elementSideRepository;
    private final HoleValidator holeValidator;
    private final HoleMapper holeMapper;

    @Override
    public HoleResponse getHoleByIds(Long elementId, Long sideId, Long holeId) {
        Hole hole = holeRepository.findByHoleIdAndSideIdAndElementId(holeId, sideId, sideId)
                .orElseThrow(() -> new HoleNotFoundException("Невозможно добавить отверстие. Проверьте правильность id детали, стороны или отверстия"));

        return holeMapper.map(hole);
    }

    @Override
    @Transactional
    public HoleResponse createHole(Long elementId, Long sideId, CreateHoleRequest request) {
        ElementSide relatedSide = elementSideRepository.findBySideIdAndElementIdWithElement(sideId, elementId)
                .orElseThrow(() -> new SideNotFoundException("Невозможно добавить отверстие. Проверьте правильность id детали или стороны"));
        double heightBySide = ElementSideUtils.getHeightBySide(relatedSide);

        Hole hole = holeMapper.map(request);
        hole.setElementSide(relatedSide);
        calculateAndSetCoordinates(hole, relatedSide, heightBySide, request.coordinateByLength(),
                request.coordinateByBreadth());

        holeValidator.validate(hole, heightBySide);

        holeRepository.save(hole);
        return holeMapper.map(hole);
    }

    @Override
    @Transactional
    public HoleResponse updateHole(Long elementId, Long sideId, Long holeId, UpdateHoleRequest request) {
        ElementSide relatedSide = elementSideRepository.findBySideIdAndElementIdWithElement(sideId, elementId)
                .orElseThrow(() -> new SideNotFoundException("Сторона для размещения отверстия не найдена. Проверьте правильность id элемента или стороны"));
        holeRepository.findByHoleIdAndSideIdWithSide(holeId, sideId)
                .orElseThrow(() -> new HoleNotFoundException("Отверстие не найдено. Проверьте правильность id стороны и отверстия"));

        double heightBySide = ElementSideUtils.getHeightBySide(relatedSide);

        Hole updatedHole = holeMapper.map(request);
        updatedHole.setId(holeId);
        updatedHole.setElementSide(relatedSide);
        calculateAndSetCoordinates(updatedHole, relatedSide, heightBySide, request.coordinateByLength(),
                request.coordinateByBreadth());

        holeValidator.validate(updatedHole, heightBySide);

        holeRepository.save(updatedHole);
        return holeMapper.map(updatedHole);
    }

    @Override
    public void deleteById(Long elementId, Long sideId, Long holeId) {
        holeRepository.deleteById(holeId);
    }

    private void calculateAndSetCoordinates(Hole hole, ElementSide side, double heightBySide,
                                            String coordinateByLength, String coordinateByBreadth) {
        double coordinateByLengthValue = calculateCoordinateValue(coordinateByLength, side, heightBySide);
        double coordinateByBreadthValue = calculateCoordinateValue(coordinateByBreadth, side, heightBySide);

        hole.setCoordinateByLength(coordinateByLengthValue);
        hole.setCoordinateByBreadth(coordinateByBreadthValue);
    }

    private double calculateCoordinateValue(String coordinate, ElementSide side, double height) {
        if (CoordinateUtils.isCoordinateNumeric(coordinate)) {
            return Double.parseDouble(coordinate);
        }

        String mathExpression = CoordinateUtils.replaceParametersWithValues(coordinate, side, height);
        Queue<MathOperation> operations = MathUtils.getOperations(mathExpression);

        return MathUtils.calculateOperationsResult(operations);
    }

}
