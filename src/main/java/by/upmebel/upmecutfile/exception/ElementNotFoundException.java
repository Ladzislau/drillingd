package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class ElementNotFoundException extends CustomException {

    private static final String DEFAULT_TITLE = "Деталь не найдена";
    private static final String DEFAULT_MESSAGE = "Детали с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public ElementNotFoundException(long id) {
        super(DEFAULT_TITLE, DEFAULT_MESSAGE.formatted(id), DEFAULT_STATUS);
    }

    public ElementNotFoundException(String message) {
        super(DEFAULT_TITLE, message, DEFAULT_STATUS);
    }

    public ElementNotFoundException(String title, String message) {
        super(title, message, DEFAULT_STATUS);
    }

    public ElementNotFoundException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }

}
