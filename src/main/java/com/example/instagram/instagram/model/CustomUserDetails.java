package com.example.instagram.instagram.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public interface CustomUserDetails extends UserDetails {
    Collection<? extends GrantedAuthority> authorities = List.of();

    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    Long getId();

    String getUuid();

    String getEmail();
}
