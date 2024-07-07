package com.example.instagram.instagram.service;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.example.instagram.instagram.dto.request.PostRequestDto;
import com.example.instagram.instagram.model.Post;

public interface PostService {
    Post savePost(PostRequestDto postDto, String userUuid);

    MappingJacksonValue getPostByUuid(String postUuid);
}
