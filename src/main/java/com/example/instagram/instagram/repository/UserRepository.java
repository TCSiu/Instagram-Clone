package com.example.instagram.instagram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginUsernameOrEmail(String loginUsername, String email);
    Optional<User> findByUuid(String uuid);
}
