package com.example.instagram.instagram.repository.impl;

import com.example.instagram.instagram.repository.custom.CommentRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CommentRepositoryImpl implements CommentRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean addReply(String commentUuid, String replyUuid) {
        String query = "Update comments SET parent_uuid = :commentUuid WHERE uuid = :replyUuid AND status = true";
        int updatedCount = entityManager.createNativeQuery(query)
            .setParameter("replyUuid", replyUuid)
            .setParameter("commentUuid", commentUuid)
            .executeUpdate();
        if (updatedCount <= 0) {
            throw new RuntimeException("Failed to add reply to comment");
        }
        return updatedCount > 0;
    }
    
}
