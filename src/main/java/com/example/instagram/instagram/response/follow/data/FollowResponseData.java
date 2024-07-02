package com.example.instagram.instagram.response.follow.data;

import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.response.BaseResponseData;

public class FollowResponseData implements BaseResponseData {

    private Follows follow;
    public FollowResponseData() {
    }

    public FollowResponseData(Follows follow) {
        this.follow = follow;
    }

    public Follows getFollow() {
        return follow;
    }

    public void setFollow(Follows follow) {
        this.follow = follow;
    }
}
