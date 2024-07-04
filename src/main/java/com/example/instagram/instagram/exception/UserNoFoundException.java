package com.example.instagram.instagram.exception;

public class UserNoFoundException extends RuntimeException {
    public UserNoFoundException(String msg) {
        super(msg);
    }
}
