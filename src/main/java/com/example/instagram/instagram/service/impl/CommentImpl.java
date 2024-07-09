package com.example.instagram.instagram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.dto.request.CommentRequestDto;
import com.example.instagram.instagram.exception.PostNotFoundException;
import com.example.instagram.instagram.exception.UserNoFoundException;
import com.example.instagram.instagram.model.Comment;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.CommentRepository;
import com.example.instagram.instagram.repository.PostRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.CommentService;
import com.example.instagram.instagram.service.FilterBeanService;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public MappingJacksonValue saveComment(CommentRequestDto commentDto, String userUuid, String postUuid, String commentUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found with UUID: " + userUuid));
        Post post = postRepository.findByUuid(postUuid).orElseThrow(() -> new PostNotFoundException("Post not found with UUID: " + postUuid));

        Comment newComment = new Comment(commentDto.getComment(), post, user);
        commentRepository.save(newComment);

        if (commentUuid != null && !commentUuid.trim().isEmpty()) {
            Comment parentComment = commentRepository.findByUuidAndStatus(commentUuid, true).orElseThrow(() -> new PostNotFoundException("Comment not found with UUID: " + commentUuid));
            commentRepository.addReply(parentComment.getUuid(), newComment.getUuid());
        }

        return getMappingComment(newComment, "Comment replied successfully");
    }

    @Override
    public MappingJacksonValue getCommentByUuid(String commentUuid) {
        Comment comment = commentRepository.findByUuidAndStatus(commentUuid, true).orElseThrow(() -> new PostNotFoundException("Comment not found with UUID: " + commentUuid));
        return getMappingComment(comment, "Comment retrieved successfully");
    }

    protected MappingJacksonValue getMappingComment(Comment comment, String message) {
        String[] commentFilterFields = { "post", "user" };
        SimpleFilterProvider filterProvider = FilterBeanService.createFilterProvider();
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "commentFilter", commentFilterFields);
        MappingJacksonValue mapping = FilterBeanService.getFilterdValue(filterProvider, comment, message);
        return mapping;
    }
}
