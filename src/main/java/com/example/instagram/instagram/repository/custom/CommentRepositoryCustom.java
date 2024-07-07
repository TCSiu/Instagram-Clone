package com.example.instagram.instagram.repository.custom;

public interface CommentRepositoryCustom {
    Boolean addReply(String commentUuid, String replyUuid);
}