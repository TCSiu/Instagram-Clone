package com.example.instagram.instagram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.dto.request.PostRequestDto;
import com.example.instagram.instagram.exception.UserNoFoundException;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.PostRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FilterBeanService;
import com.example.instagram.instagram.service.MediaService;
import com.example.instagram.instagram.service.PostService;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.transaction.Transactional;

@Service
public class PostImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MediaService mediaService;

    @Override
    @Transactional
    public Post savePost(PostRequestDto postDto, String userUuid, List<MultipartFile> files) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found"));
        Post post = new Post(postDto.getCaption(), postDto.getLocation(), user);
        postRepository.save(post);
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                mediaService.store(file, post);
            }
        }
        return post;
    }

    @Override
    public MappingJacksonValue getPostByUuid(String postUuid) {
        Post post = postRepository.getPostByUuid(postUuid);
        return getMappingPost(post, "Get Post Successfully. Post Uuid: " + postUuid);
    }

    protected MappingJacksonValue getMappingPost(Post post, String message) {
        String[] mediaFilterFields = { "post", "user" };
        String[] commentFilterFields = { "post", "user" };
        String[] postLikeFilterFields = { "post", "user" };
        String[] userFilterFields = { "followers", "followings", "userInformation" };
        SimpleFilterProvider filterProvider = FilterBeanService.createFilterProvider();
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "postFilter", new String[] {});
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "mediaFilter", mediaFilterFields);
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "commentFilter", commentFilterFields);
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "postLikeFilter", postLikeFilterFields);
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "userFilter", userFilterFields);
        MappingJacksonValue mapping = FilterBeanService.getFilterdValue(filterProvider, post, message);
        return mapping;
    }
}
