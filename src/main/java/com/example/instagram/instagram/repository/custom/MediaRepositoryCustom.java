package com.example.instagram.instagram.repository.custom;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;

public interface MediaRepositoryCustom {
    Media saveImage(String mediaType, String mediaUrl, Post post);
    void deleteImage(String userUuid);
}
