package com.robot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleBlogAlreadyExistsException(ResourceNotFoundException exception) {
        return new ResponseEntity<String>("Resource Not Found!", HttpStatus.OK);
    }
}
