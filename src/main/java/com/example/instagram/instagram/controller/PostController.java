package com.example.instagram.instagram.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.dto.PostRequestDto;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.response.post.PostResponse;
import com.example.instagram.instagram.response.post.data.PostResponseData;
import com.example.instagram.instagram.service.MediaService;
import com.example.instagram.instagram.service.PostLikeService;
import com.example.instagram.instagram.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final MediaService mediaService;
    private final PostService postService;
    private final PostLikeService postLikeService;

    public PostController(MediaService mediaService, PostService postService, PostLikeService postLikeService) {
        this.mediaService = mediaService;
        this.postService = postService;
        this.postLikeService = postLikeService;
    }

    @Transactional
    @PostMapping("/upload")
    public ResponseEntity<PostResponse> uploadImage(@Valid PostRequestDto postDto, @RequestParam("media") List<MultipartFile> files) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        Post post = postService.savePost(postDto, currentUserUuid);
        if (!files.isEmpty()) {
            for (MultipartFile file : files) {
                mediaService.store(file, post);
            }
        }
        PostResponseData responseData = new PostResponseData(post);
        PostResponse response = new PostResponse(responseData, "Post Created Successfully. Post Uuid: " + post.getUuid());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{post_uuid}")
    public ResponseEntity<PostResponse> getPost(@PathVariable String post_uuid) {
        Post post = postService.getPostByUuid(post_uuid);

        PostResponseData responseData = new PostResponseData(post);
        PostResponse response = new PostResponse(responseData, "Get Post Successfully. Post Uuid: " + post.getUuid());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{post_uuid}/like")
    @Transactional
    public ResponseEntity<PostResponse> likePost(@PathVariable String post_uuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        postLikeService.likePost(post_uuid, currentUserUuid);

        PostResponse response = new PostResponse(null, "Post Liked Successfully");

        return ResponseEntity.ok().body(response);
    }
}
