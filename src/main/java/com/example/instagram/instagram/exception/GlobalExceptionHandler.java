package com.example.instagram.instagram.exception;

import com.example.instagram.instagram.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({PasswordUnmatchedException.class})
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(PasswordUnmatchedException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler({EmailNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorResponse(exception.getMessage()));
    }
}
