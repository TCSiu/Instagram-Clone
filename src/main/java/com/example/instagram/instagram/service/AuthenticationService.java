package com.example.instagram.instagram.service;

import com.example.instagram.instagram.Dto.LoginDto;
import com.example.instagram.instagram.Dto.RegisterDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserRepository;

public interface AuthenticationService {
    User register(RegisterDto registerDto);
    User authenticate(LoginDto loginDto);

}
