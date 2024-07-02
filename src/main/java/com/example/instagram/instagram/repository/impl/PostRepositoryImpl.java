package com.example.instagram.instagram.repository.impl;

import com.example.instagram.instagram.Dto.PostDto;
import com.example.instagram.instagram.exception.PostNotFoundException;
import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Post savePost(Post post, String userUuid) {
        entityManager.persist(post);
        return post;
    }

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
