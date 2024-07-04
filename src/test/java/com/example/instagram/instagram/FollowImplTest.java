package com.example.instagram.instagram;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.instagram.instagram.common.FollowStatus;
import com.example.instagram.instagram.exception.FollowRequestAlreadyExistsException;
import com.example.instagram.instagram.exception.FollowingRequestNotFoundException;
import com.example.instagram.instagram.exception.FollowingRequestNotOwnerException;
import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.FollowRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.impl.FollowImpl;

class FollowImplTest {

    @Mock
    private FollowRepository followRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FollowImpl followService;

    private User user1, user2;
    private Follows follow;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user1 = new User(); // Assume User class has appropriate setters to set properties
        user1.setUuid("userUuid");
        userRepository.save(user1);
        user2 = new User();
        user2.setUuid("currentUserUuid");
        userRepository.save(user2);
        follow = new Follows(user1, user2, FollowStatus.PENDING);
        follow.setUuid("requestUuid");
        followRepository.saveFollows(follow);
    }

    @Test
    void followUser_Success() throws FollowRequestAlreadyExistsException {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user1));
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user2));
        when(followRepository.findFollowByFollowerUuidAndFollowingUuid(any(String.class), any(String.class))).thenReturn(Optional.empty());
        when(followRepository.saveFollows(any(Follows.class))).thenReturn(follow);

        Follows result = followService.followUser("userUuid", "currentUserUuid");
        assertEquals(FollowStatus.PENDING, result.getStatus());
    }

    @Test
    void followUser_Failure_FollowRequestAlreadyExists() {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user1));
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user2));
        // Ensure this returns a non-empty Optional to simulate an existing follow request
        when(followRepository.findFollowByFollowerUuidAndFollowingUuid(any(String.class), any(String.class))).thenReturn(Optional.of(follow));
        // Attempt to create a follow request where one already exists, expecting an exception
        assertThrows(FollowRequestAlreadyExistsException.class, () -> followService.followUser("userUuid", "currentUserUuid"));
    }

    @Test
    void followRequestApprove_Success() throws FollowingRequestNotOwnerException, FollowingRequestNotFoundException {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user2));
        when(followRepository.findFollowByUuidAndStatus(any(String.class), any(FollowStatus.class))).thenReturn(Optional.of(follow));
        when(followRepository.saveFollows(any(Follows.class))).thenReturn(follow);

        Follows result = followService.followRequestApprove("currentUserUuid", "requestUuid");
        assertEquals(FollowStatus.ACTIVE, result.getStatus());
    }

    @Test
    void followRequestReject_Success() throws FollowingRequestNotOwnerException, FollowingRequestNotFoundException {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user2));
        when(followRepository.findFollowByUuidAndStatus(any(String.class), any(FollowStatus.class))).thenReturn(Optional.of(follow));
        when(followRepository.saveFollows(any(Follows.class))).thenReturn(follow);

        Follows result = followService.followRequestReject("currentUserUuid", "requestUuid");
        assertEquals(FollowStatus.REJECT, result.getStatus());
    }

    @Test
    void updateFollowRequest_Failure_RequestNotFound() {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.of(user1));
        when(followRepository.findFollowByUuidAndStatus(any(String.class), any(FollowStatus.class))).thenReturn(Optional.empty());

        assertThrows(FollowingRequestNotFoundException.class, () -> followService.followRequestApprove("currentUserUuid", "requestUuid"));
    }

    @Test
    void findUser_Failure_UserNotFound() {
        when(userRepository.findByUuid(any(String.class))).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> followService.followUser("userUuid", "currentUserUuid"));
    }
}