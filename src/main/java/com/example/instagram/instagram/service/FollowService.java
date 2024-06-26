package com.example.instagram.instagram.service;

public interface FollowService {
    Boolean followUser(String userUuid, String currentUserUuid);
    Boolean followRequestApprove(String currentUserUuid, String requestUuid);
    Boolean followRequestReject(String currentUserUuid, String requestUuid);
}
