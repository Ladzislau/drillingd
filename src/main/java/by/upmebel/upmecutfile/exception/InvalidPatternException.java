package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class InvalidPatternException extends CustomException {

    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidPatternException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public InvalidPatternException(HttpStatus status, String message) {
        super(status, message);
    }
}
