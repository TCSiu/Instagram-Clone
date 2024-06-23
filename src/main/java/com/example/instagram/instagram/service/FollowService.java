package com.example.instagram.instagram.service;

import com.example.instagram.instagram.model.CustomUserDetails;
import com.example.instagram.instagram.model.User;

import java.util.List;

public interface FollowService {
    Boolean followUser(String userUuid, String currentUserUuid);
    Boolean followRequestApprove(String currentUserUuid, String requestUuid);
    Boolean followRequestReject(String currentUserUuid, String requestUuid);
}
