package com.example.instagram.instagram;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.instagram.instagram.dto.request.LoginRequestDto;
import com.example.instagram.instagram.dto.request.RegisterRequestDto;
import com.example.instagram.instagram.exception.EmailExistsException;
import com.example.instagram.instagram.exception.EmailNotFoundException;
import com.example.instagram.instagram.exception.PasswordUnmatchedException;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserInformationRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.impl.AuthenticationImpl;

class AuthenticationImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserInformationRepository userInformationRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_Success() {
        RegisterRequestDto registerDto = new RegisterRequestDto("John Doe", "john@example.com", "password", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User result = authenticationService.register(registerDto);

        assertNotNull(result);
        assertEquals("John Doe", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    void register_PasswordMismatch_ThrowsException() {
        RegisterRequestDto registerDto = new RegisterRequestDto("John Doe", "john@example.com", "password", "differentPassword");

        assertThrows(PasswordUnmatchedException.class, () -> authenticationService.register(registerDto));
    }

    @Test
    void register_EmailExists_ThrowsException() {
        RegisterRequestDto registerDto = new RegisterRequestDto("John Doe", "john@example.com", "password", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));
    
        assertThrows(EmailExistsException.class, () -> authenticationService.register(registerDto));
    }

    @Test
    void authenticate_Success() {
        LoginRequestDto loginDto = new LoginRequestDto("john@example.com", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        assertDoesNotThrow(() -> authenticationService.authenticate(loginDto));
    }

    @Test
    void authenticate_EmailNotFound_ThrowsException() {
        LoginRequestDto loginDto = new LoginRequestDto("nonexistent@example.com", "password");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(EmailNotFoundException.class, () -> authenticationService.authenticate(loginDto));
    }
}