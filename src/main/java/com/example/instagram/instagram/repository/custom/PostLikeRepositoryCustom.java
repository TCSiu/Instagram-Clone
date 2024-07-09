package com.example.instagram.instagram.repository.custom;

import java.util.List;
import java.util.Optional;

import com.example.instagram.instagram.model.PostLike;

public interface PostLikeRepositoryCustom {
    PostLike save(PostLike postLike);
    Boolean updateStatus(String postLikeUuid, String userUuid, Boolean status);
    Optional<PostLike> findByPostUuidAndUserUuidAndStatus(String postUuid, String userUuid, Boolean status);
    List<PostLike> findAllByUserUuid(String userUuid);
    List<PostLike> findAllByPostUuid(String postUuid);    
}
