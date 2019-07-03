package com.anz.account.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> constraintViolation(ConstraintViolationException e) {
        logger.error("Validation Error :" + e.getMessage());
        return getResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> methodArgumentMismatch(MethodArgumentTypeMismatchException e) {
        logger.error("Argument type error :" + e.getMessage() );
        return getResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> serverError(Exception e) {
        logger.error("Server Error!!!." + e.getMessage());
        return getResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponseEntity(String message, HttpStatus httpStatus) {
        ErrorMessage errorMessage = new ErrorMessage(message, httpStatus);
        return new ResponseEntity<Object>(errorMessage, errorMessage.getHttpStatus());
    }

}
