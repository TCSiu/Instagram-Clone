package com.example.instagram.instagram.response;

public interface BaseResponse<T extends BaseResponseData> {

    void setData(T data);
    T getData();
    void setMessage(String message);
    String getMessage();
}
