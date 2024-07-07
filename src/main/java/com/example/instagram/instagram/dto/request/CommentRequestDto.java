package com.example.instagram.instagram.dto.request;

import com.example.instagram.instagram.dto.BaseDto;

public class CommentRequestDto extends BaseDto {
    private String comment;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}