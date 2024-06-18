package com.example.instagram.instagram.response.error;

import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;

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
