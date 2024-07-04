package com.example.instagram.instagram.exception;

public class FollowRequestAlreadyExistsException extends RuntimeException {
    public FollowRequestAlreadyExistsException(String message) {
        super(message);
    }
}
