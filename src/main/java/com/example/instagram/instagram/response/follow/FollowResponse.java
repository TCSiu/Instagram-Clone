package com.example.instagram.instagram.response.follow;

import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;

public class FollowResponse implements BaseResponse<BaseResponseData> {
    private BaseResponseData data;

    private String message;

    public FollowResponse() {
    }

    public FollowResponse(BaseResponseData data, String message) {
        this.data = data;
        this.message = message;
    }

    @Override
    public void setData(BaseResponseData data) {
        this.data = data;
    }

    @Override
    public BaseResponseData getData() {
        return data;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
