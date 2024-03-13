package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class SideNotFoundException extends CustomException {

    private static final String DEFAULT_TITLE = "Сторона детали не найдена";
    private static final String DEFAULT_MESSAGE = "Сторона с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public SideNotFoundException(long id) {
        super(DEFAULT_TITLE, DEFAULT_MESSAGE.formatted(id), DEFAULT_STATUS);
    }

    public SideNotFoundException(String message) {
        super(DEFAULT_TITLE, message, DEFAULT_STATUS);
    }

    public SideNotFoundException(String message, HttpStatus status) {
        super(DEFAULT_TITLE, message, status);
    }

    public SideNotFoundException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }
}
