package com.example.instagram.instagram.response.user;

import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;

public class GetUsersResponse implements BaseResponse<BaseResponseData> {
    private BaseResponseData data;
    private String message;

    public GetUsersResponse() {
    }

    public GetUsersResponse(BaseResponseData data, String message) {
        this.data = data;
        this.message = message;
    }

    @Override
    public void setData(BaseResponseData data) {
        this.data = data;
    }

    @Override
    public BaseResponseData getData() {
        return this.data;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
