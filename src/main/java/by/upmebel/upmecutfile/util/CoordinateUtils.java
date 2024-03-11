package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.domain.ElementSideParameter;
import by.upmebel.upmecutfile.domain.Hole;

import java.util.regex.Pattern;

public class CoordinateUtils {

    public static final String NUMBER_REGEX = "\\s*\\d+(\\.\\d+)?\\s*$";
    private static final String LENGTH_ABBREVIATION = ElementSideParameter.LENGTH.getValue();
    private static final String BREADTH_ABBREVIATION = ElementSideParameter.BREADTH.getValue();
    private static final String HEIGHT_ABBREVIATION = ElementSideParameter.HEIGHT.getValue();

    public static boolean isCoordinateNumeric(String coordinate) {
        return Pattern.matches(NUMBER_REGEX, coordinate);
    }

    public static String replaceParametersWithValues(String coordinate, ElementSide side, double height){
        return coordinate.toUpperCase().replace(LENGTH_ABBREVIATION, side.getLength() + "")
                .replace(BREADTH_ABBREVIATION, side.getBreadth() + "")
                .replace(HEIGHT_ABBREVIATION, height + "");
    }

    public static boolean isCoordinatesValid(ElementSide side, Hole hole){
        double sideLength = side.getLength();
        double sideBreadth = side.getBreadth();
        double holeCoordinateByLength = hole.getCoordinateByLength();
        double holeCoordinateByBreadth = hole.getCoordinateByBreadth();

        return (sideLength >= holeCoordinateByLength) && (sideBreadth >= holeCoordinateByBreadth);
    }

}
