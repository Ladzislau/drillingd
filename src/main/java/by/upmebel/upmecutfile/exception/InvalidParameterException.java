package by.upmebel.upmecutfile.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class InvalidParameterException extends RuntimeException {

    private Map<String, String> parameterErrorMap;

    public InvalidParameterException(Map<String, String> parameterErrorMap) {
        super();
        this.parameterErrorMap = parameterErrorMap;
    }
}
