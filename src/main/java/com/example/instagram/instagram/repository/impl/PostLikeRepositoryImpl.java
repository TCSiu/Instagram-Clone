package com.example.instagram.instagram.repository.impl;

import java.util.List;
import java.util.Optional; // Add this import statement

import com.example.instagram.instagram.model.PostLike;
import com.example.instagram.instagram.repository.custom.PostLikeRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PostLikeRepositoryImpl implements PostLikeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostLike> findAllByUserUuid(String userUuid) {
        String hql = "FROM PostLike pl WHERE user.uuid = :userUuid AND pl.status = true";
        return entityManager.createQuery(hql, PostLike.class)
                .setParameter("userUuid", userUuid)
                .getResultList();
    }

    @Override
    public List<PostLike> findAllByPostUuid(String postUuid) {
        String hql = "FROM PostLike pl WHERE post.uuid = :postUuid AND pl.status = true";
        return entityManager.createQuery(hql, PostLike.class)
                .setParameter("postUuid", postUuid)
                .getResultList();
    }

    @Override
    public Optional<PostLike> findByPostUuidAndUserUuid(String postUuid, String userUuid, Boolean status) {
        String hql = "FROM PostLike pl WHERE user.uuid = :userUuid AND post.uuid = :postUuid AND pl.status = :status";
        return entityManager.createQuery(hql, PostLike.class)
                .setParameter("userUuid", userUuid)
                .setParameter("postUuid", postUuid)
                .setParameter("status", status)
                .getResultStream()
                .findFirst();
    }

    @Override
    public PostLike save(PostLike postLike) {
        entityManager.persist(postLike);
        return postLike;
    }

    @Override
    public Boolean updateStatus(String postUuid, String userUuid, Boolean status) { // pending: change to native query
        String hql = "UPDATE post_like SET status = :status WHERE post_uuid = :postUuid AND user_uuid = :userUuid AND status = true";
        int updatedCount = entityManager.createNativeQuery(hql)
            .setParameter("status", status)
            .setParameter("postUuid", postUuid)
            .setParameter("userUuid", userUuid)
            .executeUpdate();
        if (updatedCount <= 0) {
            throw new RuntimeException("Failed to update PostLike status");
        }
        return updatedCount > 0;
    }
}
