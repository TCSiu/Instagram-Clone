package com.example.instagram.instagram.repository;


import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.model.Follows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follows, Long> {
    Optional<Follows> findFollowByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);

    Optional<Follows> findFollowByUuidAndStatus(String followRequestUuid, FollowStatus status);
    List<Follows> findAllByFollowingUuid(String followingUuid);
    List<Follows> findAllByFollowerUuid(String followerUuid);

}
