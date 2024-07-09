package com.example.instagram.instagram.service;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.dto.request.PostRequestDto;
import com.example.instagram.instagram.model.Post;

public interface PostService {
    Post savePost(PostRequestDto postDto, String userUuid, List<MultipartFile> files);

    MappingJacksonValue getPostByUuid(String postUuid);
}
