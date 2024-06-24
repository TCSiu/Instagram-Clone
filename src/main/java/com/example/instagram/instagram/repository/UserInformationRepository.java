package com.example.instagram.instagram.repository;

import com.example.instagram.instagram.model.UserInformation;
import com.example.instagram.instagram.repository.customRepository.CustomUserInformationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long>, CustomUserInformationRepository {
    Optional<UserInformation> findByUuid(String uuid);
}
