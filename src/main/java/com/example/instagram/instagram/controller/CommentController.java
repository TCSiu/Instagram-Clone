package com.example.instagram.instagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.instagram.instagram.dto.request.CommentRequestDto;
import com.example.instagram.instagram.service.CommentService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{comment_uuid}/reply")
    @Transactional
    public ResponseEntity<MappingJacksonValue> replyComment(@PathVariable String comment_uuid, @RequestBody CommentRequestDto commentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        MappingJacksonValue comment = commentService.saveComment(commentRequestDto, currentUserUuid, "338752d3-2da3-4558-8212-9b8c1b282ed3", comment_uuid);
        return ResponseEntity.ok().body(comment);
    }

    @GetMapping("/{comment_uuid}")
    public ResponseEntity<MappingJacksonValue> getReply(@PathVariable String comment_uuid) {
        MappingJacksonValue comment = commentService.getCommentByUuid(comment_uuid);
        return ResponseEntity.ok().body(comment);
    }
}
