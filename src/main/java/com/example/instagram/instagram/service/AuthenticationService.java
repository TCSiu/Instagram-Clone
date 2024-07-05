package com.example.instagram.instagram.service;

import com.example.instagram.instagram.dto.LoginRequestDto;
import com.example.instagram.instagram.dto.RegisterRequestDto;
import com.example.instagram.instagram.model.User;

public interface AuthenticationService {
    User register(RegisterRequestDto registerDto);
    User authenticate(LoginRequestDto loginDto);

}
