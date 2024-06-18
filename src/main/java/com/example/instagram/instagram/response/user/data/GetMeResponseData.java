package com.example.instagram.instagram.response.user.data;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponseData;

public class GetMeResponseData implements BaseResponseData {
    private User user;

    public GetMeResponseData() {
    }

    public GetMeResponseData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
