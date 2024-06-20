package com.example.instagram.instagram.repository;


import com.example.instagram.instagram.model.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follows, Long> {
    Optional<Follows> findFollowByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);
    Optional<Follows> findFollowByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
