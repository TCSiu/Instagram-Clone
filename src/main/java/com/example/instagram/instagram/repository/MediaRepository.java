package com.example.instagram.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.custom.MediaRepositoryCustom;

public interface MediaRepository extends JpaRepository<Media, Long>, MediaRepositoryCustom {
    Media saveImage(String mediaType, String mediaUrl, Post post);
    void deleteImage(String userUuid);
}
