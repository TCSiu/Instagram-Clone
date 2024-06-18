package com.example.instagram.instagram.exception;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException(String msg) {
        super(msg);
    }
}
