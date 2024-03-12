package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class InvalidParameterException extends CustomException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidParameterException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public InvalidParameterException(HttpStatus status, String message) {
        super(status, message);
    }
}
