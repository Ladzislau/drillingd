package by.upmebel.upmecutfile.domain;

import lombok.Getter;

@Getter
public enum ElementSideParameter {

    LENGTH("L"),
    BREADTH("B"),
    HEIGHT("H");

    private final String value;

    ElementSideParameter(String value) {
        this.value = value;
    }

}
