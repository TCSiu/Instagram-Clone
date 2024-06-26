package com.example.instagram.instagram.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import com.example.instagram.instagram.model.CustomUserDetails;
import com.example.instagram.instagram.model.User;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String extractUuid(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsTResolver);
    String generateToken(User user);
    String generateToken(Map<String, Object> extraClaims, User user);
    long getExpirationTime();
    String buildToken(Map<String, Object> extraClaims, User user, long expiration);
    boolean isTokenValid(String token, CustomUserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    Claims extractAllClaims(String token);
    Key getSignInKey();
}
