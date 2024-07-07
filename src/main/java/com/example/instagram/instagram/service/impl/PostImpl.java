package com.example.instagram.instagram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.dto.request.PostRequestDto;
import com.example.instagram.instagram.exception.UserNoFoundException;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.PostRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FilterBeanService;
import com.example.instagram.instagram.service.PostService;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class PostImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post savePost(PostRequestDto postDto, String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found"));
        Post post = new Post(postDto.getCaption(), postDto.getLocation(), user);
        return postRepository.save(post);
    }

    @Override
    public MappingJacksonValue getPostByUuid(String postUuid) {
        Post post = postRepository.getPostByUuid(postUuid);
        String[] mediaFilterFields = { "post", "user" };
        String[] commentFilterFields = { "post", "user" };
        String[] postLikeFilterFields = { "post", "user" };
        SimpleFilterProvider filterProvider = FilterBeanService.createFilterProvider();
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "postFilter", new String[] {});
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "mediaFilter", mediaFilterFields);
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "commentFilter", commentFilterFields);
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "postLikeFilter", postLikeFilterFields);
        MappingJacksonValue mapping = FilterBeanService.getFilterdValue(filterProvider, post);
        return mapping;
    }
}
