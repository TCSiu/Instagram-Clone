package com.example.instagram.instagram.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.instagram.instagram.model.Comment;
import com.example.instagram.instagram.repository.custom.CommentRepositoryCustom;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
   Optional<Comment> findByUuidAndStatus(String uuid, Boolean status);
}
