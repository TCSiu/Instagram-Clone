package com.example.instagram.instagram.service;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.dto.request.PostRequestDto;

public interface PostService {
    MappingJacksonValue savePost(PostRequestDto postDto, String userUuid, List<MultipartFile> files);

    MappingJacksonValue getPostByUuid(String postUuid);
}
