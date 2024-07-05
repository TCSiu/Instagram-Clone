package com.example.instagram.instagram.response.follow.data;

import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.response.BaseResponseData;

public class FollowResponseData implements BaseResponseData {

    private Object follow;
    // private Follows follow;
    public FollowResponseData() {
    }

    public FollowResponseData(Object follow) {
        this.follow = follow;
    }
    // public FollowResponseData(Follows follow) {
    //     this.follow = follow;
    // }

    public Object getFollow() {
        return follow;
    }
    // public Follows getFollow() {
    //     return follow;
    // }

    public void setFollow(Follows follow) {
        this.follow = follow;
    }
}
