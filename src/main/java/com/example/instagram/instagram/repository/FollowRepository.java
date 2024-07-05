package com.example.instagram.instagram.repository;


import java.util.List;
import java.util.Optional;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.model.Follows;


public interface FollowRepository {
    Optional<Object> findFollowByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid);
    // Optional<Follows> findFollowByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);
    Optional<Follows> findFollowByFollowRequestUuidAndStatus(String followRequestUuid, FollowStatus status);
    List<Follows> findAllByUserUuid(String userUuid);
    List<Follows> findAllByTargetUserUuid(String targetUserUuid);
    Follows save(Follows follow);
}
