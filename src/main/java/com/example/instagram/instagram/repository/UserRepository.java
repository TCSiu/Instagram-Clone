package com.example.instagram.instagram.repository;

import com.example.instagram.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginUsernameOrEmail(String loginUsername, String email);
    Optional<User> findByUuid(String uuid);
}
