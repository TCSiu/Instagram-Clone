package com.example.instagram.instagram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.custom.PostRepositoryCustom;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    Optional<Post> findByUuid(String uuid);

}
