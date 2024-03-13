package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class InvalidPatternException extends CustomException {

    private static final String DEFAULT_TITLE = "Несоответствие паттерна";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidPatternException(String message) {
        super(DEFAULT_TITLE, message, DEFAULT_STATUS);
    }

    public InvalidPatternException(String message, HttpStatus status) {
        super(DEFAULT_TITLE, message, status);
    }

    public InvalidPatternException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }
}
