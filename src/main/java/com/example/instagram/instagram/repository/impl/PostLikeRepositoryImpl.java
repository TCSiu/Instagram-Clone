package com.example.instagram.instagram.repository.impl;

import java.util.List;
import java.util.Optional; // Add this import statement

import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.PostLike;
import com.example.instagram.instagram.repository.PostLikeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class PostLikeRepositoryImpl implements PostLikeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PostLike> findAllByUserUuid(String userUuid) {
        String hql = "FROM post_like pl WHERE user.uuid = :userUuid AND pl.status = true";
        return entityManager.createQuery(hql, PostLike.class)
                .setParameter("userUuid", userUuid)
                .getResultList();
    }

    @Override
    public List<PostLike> findAllByPostUuid(String postUuid) {
        String hql = "FROM post_like pl WHERE post.uuid = :postUuid AND pl.status = true";
        return entityManager.createQuery(hql, PostLike.class)
                .setParameter("postUuid", postUuid)
                .getResultList();
    }

    @Override
    public Optional<PostLike> findByPostUuidAndUserUuid(String postUuid, String userUuid, Boolean status) {
        String hql = "FROM post_like pl WHERE user.uuid = :userUuid AND pl.uuid = :postUuid AND pl.status = :status";
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
    
}
