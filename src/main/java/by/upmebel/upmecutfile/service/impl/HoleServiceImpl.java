package by.upmebel.upmecutfile.service.impl;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.domain.MathOperation;
import by.upmebel.upmecutfile.exception.ElementNotFoundException;
import by.upmebel.upmecutfile.exception.HoleNotFoundException;
import by.upmebel.upmecutfile.exception.InvalidParameterException;
import by.upmebel.upmecutfile.exception.SideNotFoundException;
import by.upmebel.upmecutfile.mapper.HoleMapper;
import by.upmebel.upmecutfile.repository.ElementSideRepository;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.service.HoleService;
import by.upmebel.upmecutfile.util.CoordinateUtils;
import by.upmebel.upmecutfile.util.ElementSideUtils;
import by.upmebel.upmecutfile.util.MathUtils;
import by.upmebel.upmecutfile.web.dto.request.hole.CreateHoleRequest;
import by.upmebel.upmecutfile.web.dto.request.hole.UpdateHoleRequest;
import by.upmebel.upmecutfile.web.dto.response.hole.HoleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Queue;

@Service
@RequiredArgsConstructor
public class HoleServiceImpl implements HoleService {

    private final HoleRepository holeRepository;
    private final ElementSideRepository elementSideRepository;
    private final HoleMapper holeMapper;

    @Override
    public HoleInfo getHoleByIds(Long elementId, Long sideId, Long holeId) {
        Hole hole = holeRepository.findByHoleIdAndSideIdAndElementId(holeId, sideId, sideId)
                .orElseThrow(() -> new HoleNotFoundException("Отверстие не найдено, проверьте правильность id детали, стороны или отверстия"));

        return holeMapper.map(hole);
    }

    @Override
    @Transactional
    public HoleInfo createHole(Long elementId, Long sideId, CreateHoleRequest request) {
        ElementSide relatedSide = elementSideRepository.findBySideIdAndElementIdWithElement(sideId, elementId)
                .orElseThrow(() -> new SideNotFoundException("Сторона для размещения отверстия не найдена. Проверьте правильность id элемента или стороны"));
        double heightBySide = ElementSideUtils.getHeightBySide(relatedSide);

        Hole hole = holeMapper.map(request);
        if (hole.getDepth() > heightBySide){
            throw new InvalidParameterException("Невозможно добавить отверстие. Глубина превышает высоту детали");
        }

        hole.setElementSide(relatedSide);
        calculateAndSetCoordinates(hole, relatedSide, heightBySide, request.coordinateByLength(),
                request.coordinateByBreadth());

        if(!CoordinateUtils.isCoordinatesValid(relatedSide, hole)){
            throw new InvalidParameterException(("""
                    Координаты отверстия превышают размеры стороны.
                    Рассчитанная длина: %.1f
                    Рассчитанная ширина: %.1f""").formatted(hole.getCoordinateByLength(), hole.getCoordinateByBreadth()));
        }

        Hole savedHole = holeRepository.save(hole);
        return holeMapper.map(savedHole);
    }

    @Override
    @Transactional
    public HoleInfo updateHole(Long elementId, Long sideId, Long holeId, UpdateHoleRequest request) {
        ElementSide relatedSide = elementSideRepository.findBySideIdAndElementIdWithElement(sideId, elementId)
                .orElseThrow(() -> new SideNotFoundException("Сторона для размещения отверстия не найдена. Проверьте правильность id элемента или стороны)"));
        holeRepository.findByHoleIdAndSideIdWithSide(holeId, sideId)
                .orElseThrow(() -> new HoleNotFoundException("Отверстие не найдено. Проверьте правильность id стороны и отверстия"));

        double heightBySide = ElementSideUtils.getHeightBySide(relatedSide);

        Hole updatedHole = holeMapper.map(request);
        if (updatedHole.getDepth() > heightBySide){
            throw new InvalidParameterException("Невозможно обновить отверстие. Глубина превышает высоту детали");
        }
        updatedHole.setId(holeId);
        updatedHole.setElementSide(relatedSide);

        calculateAndSetCoordinates(updatedHole, relatedSide, heightBySide, request.coordinateByLength(),
                request.coordinateByBreadth());

        if(!CoordinateUtils.isCoordinatesValid(relatedSide, updatedHole)){
            throw new InvalidParameterException(("""
                    Координаты отверстия превышают размеры стороны.
                    Рассчитанная длина: %.1f
                    Рассчитанная ширина: %.1f""").formatted(updatedHole.getCoordinateByLength(), updatedHole.getCoordinateByBreadth()));
        }

        holeRepository.save(updatedHole);
        return holeMapper.map(updatedHole);
    }

    @Override
    public void deleteById(Long elementId, Long sideId, Long holeId) {
        holeRepository.deleteById(holeId);
    }

    private void calculateAndSetCoordinates(Hole hole, ElementSide side, double heightBySide, String coordinateByLength, String coordinateByBreadth){
        double coordinateByLengthValue = calculateCoordinateValue(coordinateByLength, side, heightBySide);
        double coordinateByBreadthValue = calculateCoordinateValue(coordinateByBreadth, side, heightBySide);

        hole.setCoordinateByLength(coordinateByLengthValue);
        hole.setCoordinateByBreadth(coordinateByBreadthValue);
    }

    private double calculateCoordinateValue(String coordinate, ElementSide side, double height){
        if(CoordinateUtils.isCoordinateNumeric(coordinate)){
            return Double.parseDouble(coordinate);
        }

        if (height == -1){
            throw new ElementNotFoundException("Ошибка расчета координат отверстия. Детали, на которую вы хотите разместить отверстие, не существует");
        }
        String mathExpression = CoordinateUtils.replaceParametersWithValues(coordinate, side, height);
        Queue<MathOperation> operations = MathUtils.getOperations(mathExpression);

        return MathUtils.calculateOperationsResult(operations);
    }

}
