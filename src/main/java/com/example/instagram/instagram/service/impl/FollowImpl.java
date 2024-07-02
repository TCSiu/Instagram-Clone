package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.exception.FollowRequestAlreadyExistsResponse;
import com.example.instagram.instagram.exception.FollowingRequestNotFoundException;
import com.example.instagram.instagram.exception.FollowingRequestNotOwnerException;
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

    public FollowImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Follows followUser(String userUuid, String currentUserUuid) throws FollowRequestAlreadyExistsResponse {
        User follower = findUser(currentUserUuid);
        User following = findUser(userUuid);
        if (followRepository.findFollowByFollowerUuidAndFollowingUuid(follower.getUuid(), following.getUuid()).isEmpty()) {
            Follows follows = new Follows(follower, following, FollowStatus.PENDING);
            followRepository.save(follows);
            return follows;
        }
        throw new FollowRequestAlreadyExistsResponse("Follow Request Already Exists");
    }

    @Override
    public Follows followRequestApprove(String currentUserUuid, String requestUuid) {
        return updateFollowRequest(currentUserUuid, requestUuid, FollowStatus.ACTIVE);
    }

    @Override
    public Follows followRequestReject(String currentUserUuid, String requestUuid) {
        return updateFollowRequest(currentUserUuid, requestUuid, FollowStatus.REJECT);
    }

    private User findUser(String userUuid) {
        return userRepository.findByUuid(userUuid).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    private Follows updateFollowRequest(String currentUserUuid, String requestUuid, FollowStatus status) throws FollowingRequestNotOwnerException, FollowingRequestNotFoundException {
        User follower = findUser(currentUserUuid);
        Follows follow = followRepository.findFollowByUuidAndStatus(requestUuid, FollowStatus.PENDING).orElseThrow(() -> new FollowingRequestNotFoundException("Request Not Found"));
        if (follow.getFollower().equals(follower)) {
            follow.setStatus(status);
            followRepository.save(follow);
            return follow;
        }
        throw new FollowingRequestNotOwnerException("You Have No Permission Of This Request");
    }
}
