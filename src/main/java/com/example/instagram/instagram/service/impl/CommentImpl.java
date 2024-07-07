package com.example.instagram.instagram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(CommentRequestDto commentDto, String userUuid, String postUuid, String commentUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found with UUID: " + userUuid));
        Post post = postRepository.findByUuid(postUuid).orElseThrow(() -> new PostNotFoundException("Post not found with UUID: " + postUuid));

        Comment newComment = new Comment(commentDto.getComment(), post, user);
        commentRepository.save(newComment);

        if (commentUuid != null && !commentUuid.trim().isEmpty()) {
            Comment parentComment = commentRepository.findByUuidAndStatus(commentUuid, true).orElseThrow(() -> new PostNotFoundException("Comment not found with UUID: " + commentUuid));
            commentRepository.addReply(parentComment.getUuid(), newComment.getUuid());
        }

        return newComment;
    }

    @Override
    public Comment getCommentByUuid(String commentUuid) {
        return commentRepository.findByUuidAndStatus(commentUuid, true).orElseThrow(() -> new PostNotFoundException("Comment not found with UUID: " + commentUuid));
    }
}
