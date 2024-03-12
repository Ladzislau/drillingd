package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class SideNotFoundException extends CustomException {

    private static final String DEFAULT_MESSAGE = "Сторона с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public SideNotFoundException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public SideNotFoundException(long id) {
        super(DEFAULT_STATUS, DEFAULT_MESSAGE.formatted(id));
    }

    public SideNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
