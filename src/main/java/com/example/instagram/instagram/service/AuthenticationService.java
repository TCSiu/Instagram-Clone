package com.example.instagram.instagram.service;

import com.example.instagram.instagram.dto.request.LoginRequestDto;
import com.example.instagram.instagram.dto.request.RegisterRequestDto;
import com.example.instagram.instagram.model.User;

public interface AuthenticationService {
    User register(RegisterRequestDto registerDto);
    User authenticate(LoginRequestDto loginDto);

}
