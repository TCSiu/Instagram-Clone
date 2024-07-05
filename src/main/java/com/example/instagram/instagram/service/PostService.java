package com.example.instagram.instagram.service;

import com.example.instagram.instagram.dto.PostRequestDto;
import com.example.instagram.instagram.model.Post;

public interface PostService {
    Post savePost(PostRequestDto postDto, String userUuid);

    Post getPostByUuid(String postUuid);
}
