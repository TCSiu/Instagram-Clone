package com.example.instagram.instagram.controller;

import com.example.instagram.instagram.model.CustomUserDetails;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.service.FollowService;
import com.example.instagram.instagram.service.JwtService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private JwtService jwtService;

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
