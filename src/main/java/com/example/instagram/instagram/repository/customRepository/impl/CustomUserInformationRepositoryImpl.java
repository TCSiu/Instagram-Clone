package com.example.instagram.instagram.repository.customRepository.impl;

import com.example.instagram.instagram.repository.customRepository.CustomUserInformationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomUserInformationRepositoryImpl implements CustomUserInformationRepository {
    @PersistenceContext
    private EntityManager entityManager;
}
