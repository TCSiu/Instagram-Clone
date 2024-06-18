package com.example.instagram.instagram.response.auth.data;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponseData;

public class RegisterResponseData implements BaseResponseData {
    private User user;

    public RegisterResponseData() {
    }

    public RegisterResponseData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
