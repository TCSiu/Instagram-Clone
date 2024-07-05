package com.example.instagram.instagram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.dto.PostRequestDto;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.PostRepository;
import com.example.instagram.instagram.service.PostService;

@Service
public class PostImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(PostRequestDto postDto, String userUuid) {
        Post post = new Post(postDto.getCaption(), postDto.getLocation(), userUuid);
        return postRepository.savePost(post, userUuid);
    }

    @Override
    public Post getPostByUuid(String postUuid) {
        return postRepository.getPostByUuid(postUuid);
    }
}
