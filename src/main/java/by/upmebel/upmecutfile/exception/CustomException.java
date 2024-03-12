package by.upmebel.upmecutfile.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class CustomException extends RuntimeException {

    private HttpStatus status;

    private Date timestamp;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.timestamp = new Date();
    }

}
