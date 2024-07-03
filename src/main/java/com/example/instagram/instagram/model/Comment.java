package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    @Column(name = "COMMENT")
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_UUID", referencedColumnName = "UUID")
    private Post post;

    @Column(name = "STATUS")
    private Boolean status;

    public Comment() {
    }

    public Comment(String comment, Post post, Boolean status) {
        this.comment = comment;
        this.post = post;
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    

}
