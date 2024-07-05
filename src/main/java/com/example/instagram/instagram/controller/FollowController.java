package com.example.instagram.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.instagram.model.Follows;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.follow.FollowResponse;
import com.example.instagram.instagram.response.follow.data.FollowResponseData;
import com.example.instagram.instagram.service.FollowService;
import com.example.instagram.instagram.service.UserService;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;

    @Transactional
    @PostMapping("/{userUuid}")
    public ResponseEntity<FollowResponse> followUser(@PathVariable(value = "userUuid") String userUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String current_user_uuid = (String) authentication.getPrincipal();

        User target_user = userService.getUserByUuid(userUuid);

        Object follow = followService.followUser(current_user_uuid, target_user.getUuid());

        FollowResponseData responseData = new FollowResponseData(follow);
        FollowResponse response = new FollowResponse(responseData, String.format("Send Follow Request To %s Successfully", target_user.getUsername()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{followRequestUuid}/approve")
    public ResponseEntity<FollowResponse> followRequestApprove(@PathVariable(value = "followRequestUuid") String requestUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        Follows follow = followService.followRequestApprove(currentUserUuid, requestUuid);

        FollowResponseData responseData = new FollowResponseData(follow);
        FollowResponse response = new FollowResponse(responseData, "Follow Request Approved Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{followRequestUuid}/reject")
    public ResponseEntity<FollowResponse> followRequestReject(@PathVariable(value = "followRequestUuid") String requestUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        Follows follow = followService.followRequestReject(currentUserUuid, requestUuid);

        FollowResponseData responseData = new FollowResponseData(follow);
        FollowResponse response = new FollowResponse(responseData, "Follow Request Rejected Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
