package com.example.instagram.instagram.repository.impl;

import com.example.instagram.instagram.exception.PostNotFoundException;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.custom.PostRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class PostRepositoryImpl implements PostRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Post getPostByUuid(String postUuid) {
        try {
            String hql = "FROM Post p WHERE p.uuid = :uuid";
            return (Post) entityManager.createQuery(hql)
                    .setParameter("uuid", postUuid)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new PostNotFoundException("Post not found");
        }
    }
}
