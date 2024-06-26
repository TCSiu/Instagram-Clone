package com.example.instagram.instagram.controller;

import com.example.instagram.instagram.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping("/{userUuid}")
    public Boolean followUser(@PathVariable(value = "userUuid") String userUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        return followService.followUser(userUuid, currentUserUuid);
    }

    @GetMapping("/{followRequestUuid}/approve")
    public Boolean followRequestApprove(@PathVariable(value = "followRequestUuid") String requestUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        return followService.followRequestApprove(currentUserUuid, requestUuid);
    }

    @GetMapping("/{followRequestUuid}/reject")
    public Boolean followRequestReject(@PathVariable(value = "followRequestUuid") String requestUuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        return followService.followRequestReject(currentUserUuid, requestUuid);
    }
}
