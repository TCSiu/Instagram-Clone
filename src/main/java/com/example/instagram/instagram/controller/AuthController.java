package com.example.instagram.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.instagram.dto.request.LoginRequestDto;
import com.example.instagram.instagram.dto.request.RegisterRequestDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.response.BaseResponse;
import com.example.instagram.instagram.response.BaseResponseData;
import com.example.instagram.instagram.response.auth.LoginResponse;
import com.example.instagram.instagram.response.auth.RegisterResponse;
import com.example.instagram.instagram.response.auth.data.LoginResponseData;
import com.example.instagram.instagram.response.auth.data.RegisterResponseData;
import com.example.instagram.instagram.service.AuthenticationService;
import com.example.instagram.instagram.service.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<BaseResponseData>> register(@RequestBody RegisterRequestDto registerDto) {
        User user = authenticationService.register(registerDto);

        RegisterResponseData responseData = new RegisterResponseData(user);
        RegisterResponse response = new RegisterResponse(responseData, "Register success!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<BaseResponseData>> authenticate(@RequestBody LoginRequestDto loginDto) {
        User authenticatedUser = authenticationService.authenticate(loginDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponseData responseData = new LoginResponseData(jwtToken, jwtService.getExpirationTime());
        LoginResponse response = new LoginResponse(responseData, "Login Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
