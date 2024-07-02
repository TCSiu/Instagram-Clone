package com.example.instagram.instagram.repository;

import com.example.instagram.instagram.model.Post;

public interface PostRepository {
    Post savePost(Post post, String userUuid);

    Post getPostByUuid(String postUuid);
}
