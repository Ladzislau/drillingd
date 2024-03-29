package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.ElementSideParameter;
import by.upmebel.upmecutfile.domain.Hole;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CoordinateUtils {

    public static final String NUMBER_REGEX = "\\s*\\d+(\\.\\d+)?\\s*$";
    private static final String LENGTH_ABBREVIATION = ElementSideParameter.LENGTH.getValue();
    private static final String BREADTH_ABBREVIATION = ElementSideParameter.BREADTH.getValue();
    private static final String HEIGHT_ABBREVIATION = ElementSideParameter.HEIGHT.getValue();

    public static boolean isCoordinateNumeric(String coordinate) {
        return Pattern.matches(NUMBER_REGEX, coordinate);
    }

    public static String replaceParametersWithValues(String coordinate, ElementSide side, double height) {
        return coordinate.toUpperCase().replace(LENGTH_ABBREVIATION, side.getLength() + "")
                .replace(BREADTH_ABBREVIATION, side.getBreadth() + "")
                .replace(HEIGHT_ABBREVIATION, height + "");
    }

    public static Map<String, String> getCoordinatesErrors(ElementSide side, Hole hole) {
        Map<String, String> coordinateErrorMap = new HashMap<>();

        double sideLength = side.getLength();
        double sideBreadth = side.getBreadth();
        double holeCoordinateByLength = hole.getCoordinateByLength();
        double holeCoordinateByBreadth = hole.getCoordinateByBreadth();

        if (sideLength <= holeCoordinateByLength) {
            coordinateErrorMap.put("coordinateByLength",
                    "Координата должна быть меньше длины стороны детали. Текущее значение: " + holeCoordinateByLength);
        }
        if (sideBreadth <= holeCoordinateByBreadth) {
            coordinateErrorMap.put("coordinateByBreadth",
                    "Координата должна быть меньше длины стороны детали. Текущее значение: " + holeCoordinateByBreadth);
        }
        if (holeCoordinateByLength <= 0) {
            coordinateErrorMap.put("coordinateByLength",
                    "Координата должна быть больше нуля. Текущее значение: " + holeCoordinateByLength);
        }
        if (holeCoordinateByBreadth <= 0) {
            coordinateErrorMap.put("coordinateByBreadth",
                    "Координата должна быть больше нуля. Текущее значение: " + holeCoordinateByLength);
        }

        return coordinateErrorMap;
    }

}
