package by.upmebel.upmecutfile.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private String title;

    private HttpStatus status;

    public CustomException(String title, String message, HttpStatus status) {
        super(message);
        this.title = title;
        this.status = status;
    }

}
