package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class HoleNotFoundException extends CustomException {

    private static final String DEFAULT_MESSAGE = "Отверстия с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public HoleNotFoundException(String message) {
        super(DEFAULT_STATUS, message);
    }

    public HoleNotFoundException(long id) {
        super(DEFAULT_STATUS, DEFAULT_MESSAGE.formatted(id));
    }

    public HoleNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
