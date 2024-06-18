package com.example.instagram.instagram.response.user.data;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponseData;

import java.util.List;

public class GetUsersResponseData implements BaseResponseData {
    private List<User> userList;

    public GetUsersResponseData() {
    }

    public GetUsersResponseData(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
