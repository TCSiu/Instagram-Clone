package com.example.instagram.instagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
        return user;
    }

    public UserDetails loadUserByUsernameOrEmail(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email " + usernameOrEmail));
        return user;
    }

    public UserDetails loadUserByUserUuid(String userUuid) throws UsernameNotFoundException {
        User user = userRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with uuid " + userUuid));
        return user;
    }
    
}
