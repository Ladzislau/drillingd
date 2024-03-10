package by.upmebel.upmecutfile.handler;

import by.upmebel.upmecutfile.exception.CustomException;
import by.upmebel.upmecutfile.web.dto.response.error.ErrorResponse;
import by.upmebel.upmecutfile.web.dto.response.error.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFAULT_VALIDATION_ERROR_TITLE = "Ошибка валидации";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        HttpStatus responseStatus = HttpStatus.NOT_ACCEPTABLE;
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), responseStatus.value(), new Date());

        return ResponseEntity.status(responseStatus).body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        HttpStatus responseStatus = e.getStatus();
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), responseStatus.value(), e.getTimestamp());

        return ResponseEntity.status(responseStatus).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        Map<String, List<String>> errors = fieldErrors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(DEFAULT_VALIDATION_ERROR_TITLE,
                responseStatus.value(), errors);

        return ResponseEntity.status(responseStatus).body(errorResponse);
    }
}
