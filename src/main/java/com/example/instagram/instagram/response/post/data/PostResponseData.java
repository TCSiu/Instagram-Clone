package com.example.instagram.instagram.response.post.data;

import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.response.BaseResponseData;

public class PostResponseData implements BaseResponseData {
    private Post post;

    public PostResponseData(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
