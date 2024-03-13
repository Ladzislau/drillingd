package by.upmebel.upmecutfile.exception;

import org.springframework.http.HttpStatus;

public class HoleNotFoundException extends CustomException {

    private static final String DEFAULT_TITLE = "Отверстие не найдено";
    private static final String DEFAULT_MESSAGE = "Отверстия с id %d не существует";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public HoleNotFoundException(long id) {
        super(DEFAULT_TITLE, DEFAULT_MESSAGE.formatted(id), DEFAULT_STATUS);
    }

    public HoleNotFoundException(String message) {
        super(DEFAULT_TITLE, message, DEFAULT_STATUS);
    }

    public HoleNotFoundException(String message, HttpStatus status) {
        super(DEFAULT_TITLE, message, status);
    }

    public HoleNotFoundException(String title, String message, HttpStatus status) {
        super(title, message, status);
    }
}
