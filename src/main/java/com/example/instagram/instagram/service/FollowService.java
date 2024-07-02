package com.example.instagram.instagram.service;

import com.example.instagram.instagram.model.Follows;

public interface FollowService {
    Follows followUser(String userUuid, String currentUserUuid);
    Follows followRequestApprove(String currentUserUuid, String requestUuid);
    Follows followRequestReject(String currentUserUuid, String requestUuid);
}
