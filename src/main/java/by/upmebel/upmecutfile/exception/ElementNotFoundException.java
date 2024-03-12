package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class ElementNotFoundException extends CustomException {

    private static final String DEFAULT_MESSAGE = "Детали с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public ElementNotFoundException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public ElementNotFoundException(long id) {
        super(DEFAULT_STATUS, DEFAULT_MESSAGE.formatted(id));
    }

    public ElementNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

}
