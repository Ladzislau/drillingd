package by.upmebel.upmecutfile.util;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.domain.ElementSide;

import java.util.ArrayList;
import java.util.List;

public class ElementSideUtils {

    public static ElementSide getMissingSide(ElementSide side1, ElementSide side2) {
        Element relatedElement = side1.getElement() != null ? side1.getElement() : side2.getElement();
        double missingSideLength = -1;
        double missingSideBreadth = -1;

        if (side1.getLength() == side2.getBreadth()) {
            missingSideLength = Math.max(side1.getBreadth(), side2.getLength());
            missingSideBreadth = Math.min(side1.getBreadth(), side2.getLength());
        }

        if (side1.getLength() == side2.getLength()) {
            missingSideLength = Math.max(side1.getBreadth(), side2.getBreadth());
            missingSideBreadth = Math.min(side1.getBreadth(), side2.getBreadth());
        }

        if (side1.getBreadth() == side2.getLength()) {
            missingSideLength = Math.max(side2.getBreadth(), side1.getLength());
            missingSideBreadth = Math.min(side2.getBreadth(), side1.getLength());
        }

        if (side1.getBreadth() == side2.getBreadth()) {
            missingSideLength = Math.max(side2.getLength(), side1.getLength());
            missingSideBreadth = Math.min(side2.getLength(), side1.getLength());
        }

        if (missingSideLength <= 0 || missingSideBreadth <= 0) {
            return null;
        }

        return ElementSide.builder()
                .breadth(missingSideBreadth)
                .length(missingSideLength)
                .element(relatedElement)
                .build();
    }


    public static double getHeightBySide(ElementSide side) {
        Element element = side.getElement();
        if (element == null){
            return -1;
        }

        double sourceSideBreadth = side.getBreadth();
        List<ElementSide> componentSides = element.getSides();

        for (ElementSide sideToCompare : componentSides) {
            if (side.equals(sideToCompare)) {
                continue;
            }

            double comparingBreadth = sideToCompare.getBreadth();
            double comparingLength = sideToCompare.getLength();

            if (sourceSideBreadth == comparingBreadth) {
                return comparingLength;
            }

            if (sourceSideBreadth == comparingLength) {
                return comparingBreadth;
            }
        }

        return -1;
    }

    public static List<ElementSide> cloneSides(List<ElementSide> sidesToClone) {
        List<ElementSide> clonedSides = new ArrayList<>();

        sidesToClone.forEach(side ->
                clonedSides.add(side.clone()));

        return clonedSides;
    }



}
