package com.example.instagram.instagram.service;

import com.example.instagram.instagram.Dto.PostDto;
import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public interface PostService {
    Post savePost(PostDto postDto, String userUuid);

    Post getPostByUuid(String postUuid);
}
