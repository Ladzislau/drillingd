package by.upmebel.upmecutfile.validation.validator;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.Hole;
import by.upmebel.upmecutfile.exception.InvalidParameterException;
import by.upmebel.upmecutfile.util.CoordinateUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HoleValidator {

    public void validate(Hole hole, double heightBySide) {
        ElementSide relatedSide = hole.getElementSide();

        if (hole.getDepth() > heightBySide) {
            throw new InvalidParameterException(
                    Map.of("depth", "Глубина отверстия превышает высоту детали. Высота детали: " + heightBySide));
        }

        Map<String, String> coordinateErrors = CoordinateUtils.getCoordinatesErrors(relatedSide, hole);
        if (!coordinateErrors.isEmpty()) {
            throw new InvalidParameterException(coordinateErrors);
        }
    }
}
