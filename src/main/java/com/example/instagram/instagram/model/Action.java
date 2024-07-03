package com.example.instagram.instagram.model;

import com.example.instagram.instagram.common.ActionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "action")
public class Action extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    private ActionType action;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POST_UUID", referencedColumnName = "UUID")
    private Post post;

    @Column(name = "STATUS")
    private Boolean status;

    public Action() {
    }

    public Action(ActionType action, Post post, Boolean status) {
        this.action = action;
        this.post = post;
        this.status = status;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
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
