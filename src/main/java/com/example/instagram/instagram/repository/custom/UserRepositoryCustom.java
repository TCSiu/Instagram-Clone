package com.example.instagram.instagram.repository.custom;

import java.util.Optional;

import com.example.instagram.instagram.dto.entity.UserEntityDto;

public interface UserRepositoryCustom {
    public Optional<UserEntityDto> getUserEntityDtoByEmail(String email);
    public Optional<UserEntityDto> getUserEntityDtoByUuid(String userUuid);
    public Optional<UserEntityDto> getUserEntityDtoByLoginUsernameOrEmail(String loginUsername, String email);
}
