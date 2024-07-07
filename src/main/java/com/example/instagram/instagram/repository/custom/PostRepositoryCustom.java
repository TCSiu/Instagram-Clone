package com.example.instagram.instagram.repository.custom;

import com.example.instagram.instagram.model.Post;

public interface PostRepositoryCustom {
    Post getPostByUuid(String postUuid);
}
