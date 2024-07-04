package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "POST_LIKE")
public class PostLike extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_UUID", referencedColumnName = "UUID")
    private Post post;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "USER_UUID", referencedColumnName = "UUID")
    private User user;

    @Column(name = "STATUS")
    private Boolean status;

    public PostLike() {
    }

    public PostLike(Post post, User user, Boolean status) {
        this.post = post;
        this.user = user;
        this.status = status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
