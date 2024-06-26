package com.example.instagram.instagram.controller;

import com.example.instagram.instagram.Dto.UserInformationDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;
import com.example.instagram.instagram.response.user.GetMeResponse;
import com.example.instagram.instagram.response.user.GetUsersResponse;
import com.example.instagram.instagram.response.user.data.GetMeResponseData;
import com.example.instagram.instagram.response.user.data.GetUsersResponseData;
import com.example.instagram.instagram.service.UserInformationService;
import com.example.instagram.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserInformationService userInformationService;

    @GetMapping("/me")
    public ResponseEntity<BaseResponse<BaseResponseData>> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userUuid = (String) authentication.getPrincipal();

        User currentUser = userService.getUser(userUuid);

        GetMeResponseData responseData = new GetMeResponseData(currentUser);
        GetMeResponse response = new GetMeResponse(responseData, "Get user data success!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse<BaseResponseData>> allUsers() {
        List<User> users = userService.allUsers();

        GetUsersResponseData responseData = new GetUsersResponseData(users);
        GetUsersResponse response = new GetUsersResponse(responseData, "Get user list success!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("edit-information")
    public ResponseEntity<BaseResponse<BaseResponseData>> editUserInformation(@RequestBody UserInformationDto userInformationDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userUuid = (String) authentication.getPrincipal();
        User currentUser = userService.getUser(userUuid);

        userInformationService.editUserInformation(currentUser.getUuid(), userInformationDto);

        GetMeResponseData responseData = new GetMeResponseData(currentUser);
        GetMeResponse response = new GetMeResponse(responseData, "Get user data success!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
