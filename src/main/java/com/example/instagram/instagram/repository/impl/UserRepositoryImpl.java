package com.example.instagram.instagram.repository.impl;

import java.util.Optional;

import com.example.instagram.instagram.dto.entity.UserEntityDto;
import com.example.instagram.instagram.repository.custom.UserRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserEntityDto> getUserEntityDtoByUuid(String userUuid) {
        try {
            String hql = "SELECT id, uuid, email, username, password FROM users WHERE uuid = :userUuid";
            UserEntityDto userEntityDto = (UserEntityDto) entityManager.createNativeQuery(hql, UserEntityDto.class)
                    .setParameter("userUuid", userUuid)
                    .getSingleResult();
            return Optional.of(userEntityDto);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntityDto> getUserEntityDtoByEmail(String email) {
        try {
            String hql = "SELECT id, uuid, email, username, password FROM users WHERE email = :email";
            UserEntityDto userEntityDto = (UserEntityDto) entityManager.createNativeQuery(hql, UserEntityDto.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(userEntityDto);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntityDto> getUserEntityDtoByLoginUsernameOrEmail(String loginUsername, String email) {
        try {
            String hql = "SELECT id, uuid, email, username, password FROM users WHERE loginUsername = :loginUsername OR email = :email";
            UserEntityDto userEntityDto = (UserEntityDto) entityManager.createNativeQuery(hql, UserEntityDto.class)
                    .setParameter("loginUsername", loginUsername)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(userEntityDto);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    
}
