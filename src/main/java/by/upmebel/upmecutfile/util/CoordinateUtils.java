package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.*;

import java.util.regex.Pattern;

public class CoordinateUtils {

    public static final String COORDINATE_REGEX = "(\\s*([LlBbHh]|(\\d+(\\.\\d+)?))(?!\\w|\\d)\\s*)(\\s*[+\\-*/]\\s*([LlBbHh]|(\\d+(\\.\\d+)?))(?!\\w|\\d)\\s*)*";
    private static final String LENGTH_ABBREVIATION = ElementSideParameter.LENGTH.getValue();
    private static final String BREADTH_ABBREVIATION = ElementSideParameter.BREADTH.getValue();
    private static final String HEIGHT_ABBREVIATION = ElementSideParameter.HEIGHT.getValue();

    public static boolean isCoordinateValid(String coordinate) {
        return Pattern.matches(COORDINATE_REGEX, coordinate);
    }

    public static String replaceParametersWithValues(String coordinate, ElementSide side, double height){
        return coordinate.toUpperCase().replace(LENGTH_ABBREVIATION, side.getLength() + "")
                .replace(BREADTH_ABBREVIATION, side.getBreadth() + "")
                .replace(HEIGHT_ABBREVIATION, height + "");
    }

}
