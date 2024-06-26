package com.example.instagram.instagram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.UserService;

@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User getUser(String userUuid) {
        return userRepository.findByUuid(userUuid).orElse(null);
    }
}
