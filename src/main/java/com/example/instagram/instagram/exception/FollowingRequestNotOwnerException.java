package com.example.instagram.instagram.exception;

public class FollowingRequestNotOwnerException extends RuntimeException {
    public FollowingRequestNotOwnerException(String message) {
        super(message);
    }
}
