package by.upmebel.upmecutfile.handler;

import by.upmebel.upmecutfile.domain.ErrorMessage;
import by.upmebel.upmecutfile.exception.CustomException;
import by.upmebel.upmecutfile.exception.InvalidParameterException;
import by.upmebel.upmecutfile.web.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String INTERNAL_SERVER_ERROR_TITLE = "Внутренняя ошибка сервера";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        HttpStatus responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorMessage error = new ErrorMessage(INTERNAL_SERVER_ERROR_TITLE, responseStatus.value(), e.getMessage());

        ErrorResponse response = new ErrorResponse(new Date(), Collections.singletonList(error));
        return ResponseEntity.status(responseStatus).body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        HttpStatus responseStatus = e.getStatus();
        ErrorMessage error = new ErrorMessage(e.getTitle(), responseStatus.value(), e.getMessage());

        ErrorResponse response = new ErrorResponse(new Date(), Collections.singletonList(error));
        return ResponseEntity.status(responseStatus).body(response);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> parameterErrorMap = e.getParameterErrorMap();
        List<ErrorMessage> errors = new ArrayList<>();

        for (Map.Entry<String, String> entry : parameterErrorMap.entrySet()) {
            ErrorMessage error = new ErrorMessage(entry.getKey(), responseStatus.value(), entry.getValue());
            errors.add(error);
        }

        ErrorResponse response = new ErrorResponse(new Date(), errors);
        return ResponseEntity.status(responseStatus).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ErrorMessage> errors = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            ErrorMessage error = new ErrorMessage(fieldError.getField(), responseStatus.value(), fieldError.getDefaultMessage());
            errors.add(error);
        });

        ErrorResponse response = new ErrorResponse(new Date(), errors);
        return ResponseEntity.status(responseStatus).body(response);
    }
}
