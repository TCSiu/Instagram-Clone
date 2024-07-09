package com.example.instagram.instagram.dto.entity;

import com.example.instagram.instagram.model.CustomUserDetails;

public class UserEntityDto implements CustomUserDetails {
    private Long id;
    private String uuid;
    private String email;
    private String username;
    private String password;

    public UserEntityDto() {
    }

    public UserEntityDto(Long id, String uuid, String email, String loginUsername, String password) {
        this.id = id;
        this.uuid = uuid;
        this.email = email;
        this.username = loginUsername;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public Long getId() {
        return this.id;
    }
    @Override
    public String getUuid() {
        return this.uuid;
    }
    @Override
    public String getEmail() {
        return this.email;
    }
    
}
