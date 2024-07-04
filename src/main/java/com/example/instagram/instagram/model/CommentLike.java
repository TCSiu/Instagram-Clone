package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMENT_LIKE")
public class CommentLike extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "COMMENT_UUID", referencedColumnName = "UUID")
    private Comment comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_UUID", referencedColumnName = "UUID")
    private User user;

    @Column(name = "STATUS")
    private Boolean status;

    public CommentLike() {
    }

    public CommentLike(Comment comment, User user, Boolean status) {
        this.comment = comment;
        this.user = user;
        this.status = status;
    }

    public Comment getComment() {
        return comment;
    }

    public void setPost(Comment comment) {
        this.comment = comment;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
