package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.model.CustomUserDetails;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.FollowRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowImpl(
            FollowRepository followRepository,
            UserRepository userRepository
    ) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean followUser(String userUuid, CustomUserDetails currentUser) {
        Optional<User> follower = userRepository.findByUuid(currentUser.getUuid());
        Optional<User> following = userRepository.findByUuid(userUuid);
        if (follower.isPresent() && following.isPresent()) {
            Optional<Follows> follow = followRepository.findFollowByFollowerUuidAndFollowingUuid(follower.get().getUuid(), following.get().getUuid());
//            Optional<Follows> follow = followRepository.findFollowByFollowerIdAndFollowingId(follower.get().getId(), following.get().getId());
            if (follow.isEmpty()) {
                Follows follows = new Follows();
                follows.setFollower(follower.get());
                follows.setFollowing(following.get());
                follows.setStatus(FollowStatus.PENDING);
                followRepository.save(follows);
                return true;
            }
        }
        return false;
    }
}
