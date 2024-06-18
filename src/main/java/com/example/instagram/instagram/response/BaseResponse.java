package com.example.instagram.instagram.response;

import com.example.instagram.instagram.model.BaseEntity;

public interface BaseResponse<T extends BaseResponseData> {

    void setData(T data);
    T getData();
    void setMessage(String message);
    String getMessage();
}
