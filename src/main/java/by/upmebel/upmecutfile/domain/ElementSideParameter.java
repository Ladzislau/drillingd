package by.upmebel.upmecutfile.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ElementSideParameter {

    LENGTH("L"),
    BREADTH("B"),
    HEIGHT("H");

    private static final Map<String, ElementSideParameter> BY_VALUE = new HashMap<>();
    private final String value;

    ElementSideParameter(String value) {
        this.value = value;
    }

    public static ElementSideParameter fromValue(String value){
        if(BY_VALUE.isEmpty()){
            for (ElementSideParameter parameter : values()) {
                BY_VALUE.put(parameter.value, parameter);
            }
        }

        return BY_VALUE.get(value);
    }

}
