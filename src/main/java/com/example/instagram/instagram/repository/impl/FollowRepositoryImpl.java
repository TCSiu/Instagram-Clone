package com.example.instagram.instagram.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.common.FollowStatus;
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
    public List<Follows> findAllByFollowerUuid(String followerUuid) {
        try {
            String hql = "FROM follows f WHERE f.follower_uuid = :follower_uuid";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("follower_uuid", followerUuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    @Override
    public List<Follows> findAllByFollowingUuid(String followingUuid) {
        try {
            String hql = "FROM follows f WHERE f.following_uuid = :following_uuid";
            return entityManager.createQuery(hql, Follows.class)
                    .setParameter("following_uuid", followingUuid)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PostNotFoundException("No follows found for the given criteria");
        }
    }

    @Override
    public Optional<Follows> findFollowByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid) {
        String hql = "FROM follows f WHERE following.uuid = :following_uuid AND follower.uuid = :follower_uuid";
        return entityManager.createQuery(hql, Follows.class)
                .setParameter("following_uuid", followingUuid)
                .setParameter("follower_uuid", followerUuid)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Follows> findFollowByUuidAndStatus(String followRequestUuid, FollowStatus status) {
        String hql = "FROM follows f WHERE f.uuid = :request_uuid AND f.status = :status";
        return entityManager.createQuery(hql, Follows.class)
                .setParameter("request_uuid", followRequestUuid)
                .setParameter("status", status)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Follows saveFollows(Follows follow) {
        entityManager.persist(follow);
        return follow;
    }
    
}
