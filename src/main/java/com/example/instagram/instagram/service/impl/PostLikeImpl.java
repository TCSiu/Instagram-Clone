package com.example.instagram.instagram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.exception.PostLikedNotFoundException;
import com.example.instagram.instagram.exception.UserNoFoundException;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.model.PostLike;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.PostLikeRepository;
import com.example.instagram.instagram.repository.PostRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.PostLikeService;

@Service
public class PostLikeImpl implements PostLikeService {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void likePost(String postUuid, String userUuid) {
        Post post = postRepository.getPostByUuid(postUuid);
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found with UUID: " + userUuid));
        PostLike postLike = new PostLike(post, user, true);
        postLikeRepository.save(postLike);
    }

    @Override
    public void unlikePost(String postUuid, String userUuid) {
        PostLike postLike = postLikeRepository.findByPostUuidAndUserUuid(postUuid, userUuid, true).orElseThrow(() -> new PostLikedNotFoundException("Post not liked by user with UUID: " + userUuid));
        postLike.setStatus(false);
        postLikeRepository.save(postLike);
    }

    @Override
    public Boolean isPostLiked(String postUuid, String userUuid) {
        return postLikeRepository.findByPostUuidAndUserUuid(postUuid, userUuid, true).isPresent();

    }

    @Override
    public Integer countPostLikes(String postUuid) {
        return postLikeRepository.findAllByPostUuid(postUuid).size();
    }

    @Override
    public Integer countUserLikes(String userUuid) {
        return postLikeRepository.findAllByUserUuid(userUuid).size();
    }
    
}
