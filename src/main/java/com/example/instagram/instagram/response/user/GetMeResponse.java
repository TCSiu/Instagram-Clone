package com.example.instagram.instagram.response.user;

import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;
import com.example.instagram.instagram.response.user.data.GetMeResponseData;

public class GetMeResponse implements BaseResponse<BaseResponseData> {
    private BaseResponseData data;
    private String message;

    public GetMeResponse() {
    }

    public GetMeResponse(BaseResponseData data, String message) {
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
