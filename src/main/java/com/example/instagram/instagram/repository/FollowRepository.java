package com.example.instagram.instagram.repository;


import java.util.List;
import java.util.Optional;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.model.Follows;


public interface FollowRepository {
    Optional<Follows> findFollowByFollowerUuidAndFollowingUuid(String followerUuid, String followingUuid);
    Optional<Follows> findFollowByUuidAndStatus(String followRequestUuid, FollowStatus status);
    List<Follows> findAllByFollowingUuid(String followingUuid);
    List<Follows> findAllByFollowerUuid(String followerUuid);
    Follows saveFollows(Follows follow);
}
