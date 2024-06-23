package com.example.instagram.instagram.exception;

import com.example.instagram.instagram.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    protected ResponseEntity<ErrorResponse> generateResponseEntity(HttpStatus status, Exception exception) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(exception.getMessage()));
    }
    @ExceptionHandler({PasswordUnmatchedException.class})
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(PasswordUnmatchedException exception) {
        return this.generateResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException exception) {
        return this.generateResponseEntity(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({EmailNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(EmailNotFoundException exception) {
        return this.generateResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({FollowingRequestNotFoundException.class})
    public ResponseEntity<ErrorResponse> HandleFollowingRequestNotFoundException(FollowingRequestNotFoundException exception) {
        return this.generateResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> HandleBadCredentialsException(BadCredentialsException exception) {
        return this.generateResponseEntity(HttpStatus.BAD_REQUEST, exception);
    }
}
