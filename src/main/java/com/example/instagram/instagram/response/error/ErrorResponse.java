package com.example.instagram.instagram.response.error;

public class ErrorResponse {
    private String message;
    public ErrorResponse() {
    }
    public ErrorResponse(String message) {
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
