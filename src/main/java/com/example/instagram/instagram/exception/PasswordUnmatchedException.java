package com.example.instagram.instagram.exception;

public class PasswordUnmatchedException extends RuntimeException {
    public PasswordUnmatchedException(String msg) {
        super(msg);
    }
}
