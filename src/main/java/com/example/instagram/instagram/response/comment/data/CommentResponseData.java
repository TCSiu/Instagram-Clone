package com.example.instagram.instagram.response.comment.data;

import com.example.instagram.instagram.model.Comment;
import com.example.instagram.instagram.response.BaseResponseData;

public class CommentResponseData implements BaseResponseData {
    private Comment comment;

    public CommentResponseData() {
    }

    public CommentResponseData(Comment comment) {
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
