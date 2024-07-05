package com.example.instagram.instagram.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.dto.FollowEntityDto;
import com.example.instagram.instagram.exception.PostNotFoundException;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.repository.FollowRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class FollowRepositoryImpl implements FollowRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Follows> findAllByUserUuid(String userUuid) {
        try {
            String hql = "FROM follows f WHERE f.user_uuid = :userUuid";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("userUuid", userUuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    @Override
    public List<Follows> findAllByTargetUserUuid(String targetUseruuid) {
        try {
            String hql = "FROM follows f WHERE f.target_user_uuid = :targetUseruuid";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("targetUseruuid", targetUseruuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    // @Override
    // public Optional<Object> findFollowByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid) {
    //     String hql = "FROM follows f WHERE user.uuid = :user_uuid AND target_user.uuid = :target_user_uuid AND f.status != 'DELETE'";
    //     try {
    //         Follows follow = entityManager.createQuery(hql, Follows.class)
    //                 .setParameter("user_uuid", userUuid)
    //                 .setParameter("target_user_uuid", targetUserUuid)
    //                 .getSingleResult();
    //         follow.getTarget_user();
    //         return Optional.of(follow);
    //     } catch (NoResultException e) {
    //         return Optional.empty();
    //     }
    // }

    @Override
    public Optional<Object> findFollowByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid) {
        String hql = "SELECT id, uuid, user_uuid, target_user_uuid, status, created_at, created_by, updated_at, updated_by FROM follows WHERE user_uuid = :user_uuid AND target_user_uuid = :target_user_uuid AND status != 'DELETE'";
        try {
            Object follow = (Object) entityManager.createNativeQuery(hql, FollowEntityDto.class)
                    .setParameter("user_uuid", userUuid)
                    .setParameter("target_user_uuid", targetUserUuid)
                    .getSingleResult();
            // follow.getTarget_user();
            return Optional.of(follow);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Follows> findFollowByFollowRequestUuidAndStatus(String followRequestUuid, FollowStatus status) {
        String hql = "FROM follows f WHERE f.uuid = :request_uuid AND f.status = :status";
        return entityManager.createQuery(hql, Follows.class)
                .setParameter("request_uuid", followRequestUuid)
                .setParameter("status", status)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Follows save(Follows follow) {
        entityManager.persist(follow);
        return follow;
    }
    
}
