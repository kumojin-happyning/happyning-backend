package fr.octocorn.happyningbackend.event.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = "Validation failed: " + ex.getConstraintViolations().iterator().next().getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(errorMessage);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}