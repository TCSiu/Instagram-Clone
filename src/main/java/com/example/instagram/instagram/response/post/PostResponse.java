package com.example.instagram.instagram.response.post;

import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;

public class PostResponse implements BaseResponse<BaseResponseData> {
    private BaseResponseData data;
    private String message;

    public PostResponse() {
    }

    public PostResponse(BaseResponseData data, String message) {
        this.data = data;
        this.message = message;
    }

    @Override
    public BaseResponseData getData() {
        return data;
    }

    @Override
    public void setData(BaseResponseData data) {
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
