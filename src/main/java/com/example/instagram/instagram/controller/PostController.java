package com.example.instagram.instagram.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.dto.request.CommentRequestDto;
import com.example.instagram.instagram.dto.request.PostRequestDto;
import com.example.instagram.instagram.model.Comment;
import com.example.instagram.instagram.response.comment.CommentResponse;
import com.example.instagram.instagram.response.comment.data.CommentResponseData;
import com.example.instagram.instagram.response.post.PostResponse;
import com.example.instagram.instagram.service.CommentService;
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
    private final CommentService commentService;

    public PostController(MediaService mediaService, PostService postService, PostLikeService postLikeService, CommentService commentService) {
        this.mediaService = mediaService;
        this.postService = postService;
        this.postLikeService = postLikeService;
        this.commentService = commentService;
    }

    @Transactional
    @PostMapping("/upload")
    public ResponseEntity<MappingJacksonValue> uploadImage(@Valid PostRequestDto postDto, @RequestParam("media") List<MultipartFile> files) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        MappingJacksonValue response = postService.savePost(postDto, currentUserUuid, files);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{post_uuid}")
    public ResponseEntity<MappingJacksonValue> getPost(@PathVariable String post_uuid) {
        MappingJacksonValue response = postService.getPostByUuid(post_uuid);
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

    @GetMapping("/{post_uuid}/unlike")
    @Transactional
    public ResponseEntity<PostResponse> unlikePost(@PathVariable String post_uuid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        if (postLikeService.unlikePost(post_uuid, currentUserUuid)) {
            PostResponse response = new PostResponse(null, "Post Unliked Successfully");
            return ResponseEntity.ok().body(response);
        }

        PostResponse response = new PostResponse(null, "Post Unliked Failed");
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/{post_uuid}/comment")
    @Transactional
    public ResponseEntity<CommentResponse> addComment(@PathVariable String post_uuid, @RequestBody CommentRequestDto commentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserUuid = (String) authentication.getPrincipal();

        Comment comment = commentService.saveComment(commentRequestDto, currentUserUuid, post_uuid, null);
        
        CommentResponseData responseData = new CommentResponseData(comment);
        CommentResponse response = new CommentResponse(responseData, "Comment Created Successfully. Comment Uuid: " + comment.getUuid());

        return ResponseEntity.ok().body(response);
    }
}
