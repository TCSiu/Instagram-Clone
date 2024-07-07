package com.example.instagram.instagram.service;

import com.example.instagram.instagram.dto.request.CommentRequestDto;
import com.example.instagram.instagram.model.Comment;

public interface CommentService {
    Comment saveComment(CommentRequestDto commentDto, String userUuid, String postUuid, String commentUuid);
    Comment getCommentByUuid(String commentUuid);
}
