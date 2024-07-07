package com.example.instagram.instagram.repository.custom;


import java.util.List;
import java.util.Optional;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.model.Follows;


public interface FollowRepositoryCustom {
    Optional<Follows> getFollowRecordByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid);
    Optional<Follows> getFollowRecordByFollowRequestUuidAndStatus(String followRequestUuid, FollowStatus status);

    List<Follows> findAllRecordsByUserUuid(String userUuid);
    List<Follows> findAllRecordsByTargetUserUuid(String targetUserUuid);

}
