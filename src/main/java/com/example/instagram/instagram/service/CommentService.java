package com.example.instagram.instagram.service;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.example.instagram.instagram.dto.request.CommentRequestDto;

public interface CommentService {
    MappingJacksonValue saveComment(CommentRequestDto commentDto, String userUuid, String postUuid, String commentUuid);
    MappingJacksonValue getCommentByUuid(String commentUuid);
}
