package com.example.instagram.instagram.service;

public interface PostLikeService {
    void likePost(String postUuid, String userUuid);
    void unlikePost(String postUuid, String userUuid);
    Boolean isPostLiked(String postUuid, String userUuid);
    Integer countPostLikes(String postUuid);
    Integer countUserLikes(String userUuid);
}
