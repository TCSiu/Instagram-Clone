package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
