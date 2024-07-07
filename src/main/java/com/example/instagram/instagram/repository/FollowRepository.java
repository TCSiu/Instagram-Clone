package com.example.instagram.instagram.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.repository.custom.FollowRepositoryCustom;

@Repository
public interface FollowRepository extends JpaRepository<Follows, Long>, FollowRepositoryCustom {
    Optional<Follows> getFollowByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid);
    int countByUserUuidAndTargetUserUuid(String userUuid, String targetUserUuid);

    List<Follows> findAllByUserUuid(String userUuid);
    List<Follows> findAllByTargetUserUuid(String userUuid);
}
