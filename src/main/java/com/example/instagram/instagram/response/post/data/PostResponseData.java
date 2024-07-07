package com.example.instagram.instagram.response.post.data;

import com.example.instagram.instagram.response.BaseResponseData;

public class PostResponseData implements BaseResponseData {
    private Object post;

    public PostResponseData(Object post) {
        this.post = post;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }
}
