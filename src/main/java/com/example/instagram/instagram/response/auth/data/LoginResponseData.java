package com.example.instagram.instagram.response.auth.data;

import com.example.instagram.instagram.response.BaseResponseData;

public class LoginResponseData implements BaseResponseData{
    private String jwt;
    private long expiresIn;

    public LoginResponseData() {
    }

    public LoginResponseData(String jwt, long expiresIn) {
        this.jwt = jwt;
        this.expiresIn = expiresIn;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
