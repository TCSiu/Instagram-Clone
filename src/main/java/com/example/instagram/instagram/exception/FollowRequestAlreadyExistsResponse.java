package com.example.instagram.instagram.exception;

public class FollowRequestAlreadyExistsResponse extends RuntimeException {
    public FollowRequestAlreadyExistsResponse(String message) {
        super(message);
    }
}
