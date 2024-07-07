package com.example.instagram.instagram.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.exception.FollowRequestAlreadyExistsException;
import com.example.instagram.instagram.exception.FollowingRequestNotFoundException;
import com.example.instagram.instagram.exception.FollowingRequestNotOwnerException;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.FollowRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FollowService;

@Service
public class FollowImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Follows followUser(String current_user_uuid, String target_user_uuid) throws FollowRequestAlreadyExistsException {
        if (current_user_uuid == null || current_user_uuid.isEmpty() || target_user_uuid == null || target_user_uuid.isEmpty()) {
            throw new IllegalArgumentException("User UUIDs cannot be null or empty.");
        }
        if (current_user_uuid.equals(target_user_uuid)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }
        User target_user = findUser(target_user_uuid);
        User current_user = findUser(current_user_uuid);

        int existingFollowsCount = followRepository.countByUserUuidAndTargetUserUuid(current_user_uuid, target_user_uuid);
        if (existingFollowsCount > 0) {
            throw new FollowRequestAlreadyExistsException("Follow Request Already Exists");
        }
        Follows follows = new Follows(current_user, target_user, FollowStatus.PENDING);
        followRepository.save(follows);
        return follows;
    }

    @Override
    public Follows followRequestApprove(String currentUserUuid, String requestUuid) {
        return updateFollowRequest(currentUserUuid, requestUuid, FollowStatus.ACTIVE);
    }

    @Override
    public Follows followRequestReject(String currentUserUuid, String requestUuid) {
        return updateFollowRequest(currentUserUuid, requestUuid, FollowStatus.DELETE);
    }

    @Override
    public List<Follows> getFollowsRequestList(String userUuid) {
        return followRepository.findAllByUserUuid(userUuid);
    }

    private User findUser(String userUuid) {
        return userRepository.findByUuid(userUuid).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    private Follows updateFollowRequest(String currentUserUuid, String requestUuid, FollowStatus status) throws FollowingRequestNotOwnerException, FollowingRequestNotFoundException {
        User target_user = findUser(currentUserUuid);
        Follows follow = followRepository.getFollowRecordByFollowRequestUuidAndStatus(requestUuid, FollowStatus.PENDING).orElseThrow(() -> new FollowingRequestNotFoundException("Request Not Found"));
        if (follow.getTarget_user().equals(target_user)) {
            follow.setStatus(status);
            followRepository.save(follow);
            return follow;
        }
        throw new FollowingRequestNotOwnerException("You do not have permission to modify this request");
    }
}
