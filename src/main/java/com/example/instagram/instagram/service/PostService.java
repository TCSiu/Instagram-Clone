package com.example.instagram.instagram.service;

import com.example.instagram.instagram.Dto.PostDto;
import com.example.instagram.instagram.model.Post;

public interface PostService {
    Post savePost(PostDto postDto, String userUuid);

    Post getPostByUuid(String postUuid);
}
