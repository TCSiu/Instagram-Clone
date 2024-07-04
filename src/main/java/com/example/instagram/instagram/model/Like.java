package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIKE")
public class Like extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_UUID", referencedColumnName = "UUID")
    private Post post;

    @Column(name = "STATUS")
    private Boolean status;

    public Like() {
    }

    public Like(Post post, Boolean status) {
        this.post = post;
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
    
}
