package com.example.instagram.instagram.repository.impl;

import java.util.List;
import java.util.Optional;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.exception.PostNotFoundException;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.repository.custom.FollowRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class FollowRepositoryImpl implements FollowRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Follows> findAllRecordsByUserUuid(String userUuid) {
        try {
            String hql = "FROM follows f WHERE user.uuid = :userUuid AND status = 'PENDING'";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("userUuid", userUuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    @Override
    public List<Follows> findAllRecordsByTargetUserUuid(String targetUseruuid) {
        try {
            String hql = "FROM Follows f WHERE f.target_user_uuid = :targetUseruuid";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("targetUseruuid", targetUseruuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    @Override
    public Optional<Follows> getFollowRecordByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid) {
        String hql = "FROM Follows f WHERE user.uuid = :user_uuid AND target_user.uuid = :target_user_uuid AND status != 'DELETE'";
        try {
            Follows follow = (Follows) entityManager.createQuery(hql, Follows.class)
                    .setParameter("user_uuid", userUuid)
                    .setParameter("target_user_uuid", targetUserUuid)
                    .getSingleResult();
            return Optional.of(follow);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Follows> getFollowRecordByFollowRequestUuidAndStatus(String followRequestUuid, FollowStatus status) {
        String hql = "FROM Follows f WHERE f.uuid = :request_uuid AND f.status = :status";
        return entityManager.createQuery(hql, Follows.class)
                .setParameter("request_uuid", followRequestUuid)
                .setParameter("status", status)
                .getResultStream()
                .findFirst();
    }  
}
