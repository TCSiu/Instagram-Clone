package com.example.instagram.instagram.service;

import io.jsonwebtoken.Claims;
import com.example.instagram.instagram.model.User;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUuid(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsTResolver);
    String generateToken(User user);
    String generateToken(Map<String, Object> extraClaims, User user);
    long getExpirationTime();
    String buildToken(Map<String, Object> extraClaims, User user, long expiration);
    boolean isTokenValid(String token, User user);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    Claims extractAllClaims(String token);
    Key getSignInKey();
}
