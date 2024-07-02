package com.example.instagram.instagram.repository.impl;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.MediaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MediaRepositoryImpl implements MediaRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Media saveImage(String mediaType, String mediaUrl, Post post) {
        Media media = new Media(mediaType, mediaUrl);
        media.setPost(post);
        entityManager.persist(media);
        return media;
    }

    @Override
    public void deleteImage(String userUuid) {

    }
}
