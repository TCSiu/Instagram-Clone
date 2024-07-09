package com.example.instagram.instagram.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserEntityDtoByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
    }

    public UserDetails loadUserByUsernameOrEmail(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.getUserEntityDtoByLoginUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email " + usernameOrEmail));
    }

    public UserDetails loadUserByUuid(String userUuid) throws UsernameNotFoundException {
        return userRepository.getUserEntityDtoByUuid(userUuid)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with uuid " + userUuid));
    }
    
}
