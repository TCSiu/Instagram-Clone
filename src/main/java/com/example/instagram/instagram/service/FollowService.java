package com.example.instagram.instagram.service;

import com.example.instagram.instagram.model.CustomUserDetails;

public interface FollowService {
    Boolean followUser(String userUuid, CustomUserDetails currentUser);
}
