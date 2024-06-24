package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.Dto.LoginDto;
import com.example.instagram.instagram.Dto.RegisterDto;
import com.example.instagram.instagram.exception.EmailExistsException;
import com.example.instagram.instagram.exception.EmailNotFoundException;
import com.example.instagram.instagram.exception.PasswordUnmatchedException;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.model.UserInformation;
import com.example.instagram.instagram.repository.UserInformationRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationImpl(
        UserRepository userRepository,
        UserInformationRepository userInformationRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPassword_confirmation())) {
            throw new PasswordUnmatchedException("Password and Password Confirmation are not the same!");
        }
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new EmailExistsException("Email already exists!");
        }

        User newUser = new User(
                registerDto.getName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword())
        );

        UserInformation newUserInformation = new UserInformation();
        userInformationRepository.save(newUserInformation);

        newUser.setUserInformation(newUserInformation);

        return userRepository.save(newUser);
    }

    @Override
    public User authenticate(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(),
                    loginDto.getPassword()
            )
        );
        return user;
    }
}
