package com.example.instagram.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.PostLike;
import com.example.instagram.instagram.repository.custom.PostLikeRepositoryCustom;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long>, PostLikeRepositoryCustom {
    Boolean existsByPostUuidAndUserUuidAndStatus(String postUuid, String userUuid, Boolean status);
}
