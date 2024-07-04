package com.example.instagram.instagram.repository;

import java.util.List; // Add this import statement
import java.util.Optional; // Add this import statement

import com.example.instagram.instagram.model.PostLike;

public interface PostLikeRepository {
    PostLike save(PostLike postLike);
    Optional<PostLike> findByPostUuidAndUserUuid(String postUuid, String userUuid, Boolean status);
    List<PostLike> findAllByUserUuid(String userUuid);
    List<PostLike> findAllByPostUuid(String postUuid);
}
