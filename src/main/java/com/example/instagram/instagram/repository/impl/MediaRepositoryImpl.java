package com.example.instagram.instagram.repository.impl;

import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.custom.MediaRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MediaRepositoryImpl implements MediaRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Media saveImage(String mediaType, String mediaUrl, Post post) {
        Media media = new Media(mediaUrl, mediaType);
        media.setPost(post);
        entityManager.persist(media);
        return media;
    }

    @Override
    public void deleteImage(String userUuid) {

    }
}
