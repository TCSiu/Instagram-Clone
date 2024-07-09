package com.example.instagram.instagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.instagram.dto.request.UserInformationRequestDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;
import com.example.instagram.instagram.response.user.GetUsersResponse;
import com.example.instagram.instagram.response.user.data.GetUsersResponseData;
import com.example.instagram.instagram.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<MappingJacksonValue> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userUuid = (String) authentication.getPrincipal();

        MappingJacksonValue currentUser = userService.getUserByUuid(userUuid);

        return ResponseEntity.ok().body(currentUser);
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse<BaseResponseData>> allUsers() {
        List<User> users = userService.allUsers();

        GetUsersResponseData responseData = new GetUsersResponseData(users);
        GetUsersResponse response = new GetUsersResponse(responseData, "Get user list success!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("edit-information")
    public ResponseEntity<MappingJacksonValue> editUserInformation(@RequestBody UserInformationRequestDto userInformationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userUuid = (String) authentication.getPrincipal();
        MappingJacksonValue currentUser = userService.editUserInformation(userUuid, userInformationDto);

        return ResponseEntity.ok().body(currentUser);
    }
}
