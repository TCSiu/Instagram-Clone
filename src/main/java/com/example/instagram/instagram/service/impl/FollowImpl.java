package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.exception.FollowingRequestNotFoundException;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.FollowRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FollowService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Boolean followUser(String userUuid, String currentUserUuid) {
        User follower = userRepository.findByUuid(currentUserUuid).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
        User following = userRepository.findByUuid(userUuid).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
        Optional<Follows> follow = followRepository.findFollowByFollowerUuidAndFollowingUuid(follower.getUuid(), following.getUuid());
        if (follow.isEmpty()) {
            Follows follows = new Follows();
            follows.setFollower(follower);
            follows.setFollowing(following);
            follows.setStatus(FollowStatus.PENDING);
            followRepository.save(follows);
            return true;
        }
        return false;
    }

    @Override
    public Boolean followRequestApprove(String currentUserUuid, String requestUuid) {
        User follower = userRepository.findByUuid(currentUserUuid).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
        Follows follow = followRepository.findFollowByUuidAndStatus(requestUuid, FollowStatus.PENDING).orElseThrow(
                () -> new FollowingRequestNotFoundException("Request Not Found")
        );
        if (follow.getFollower().equals(follower)) {
            follow.setStatus(FollowStatus.ACTIVE);
            followRepository.save(follow);
            return true;
        }
        return false;
    }

    @Override
    public Boolean followRequestReject(String currentUserUuid, String requestUuid) {
        User follower = userRepository.findByUuid(currentUserUuid).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );
        Follows follow = followRepository.findFollowByUuidAndStatus(requestUuid, FollowStatus.PENDING).orElseThrow(
                () -> new FollowingRequestNotFoundException("Request Not Found")
        );
        if (follow.getFollower().equals(follower)) {
            follow.setStatus(FollowStatus.REJECT);
            followRepository.save(follow);
            return true;
        }
        return false;
    }
}
