package com.example.instagram.instagram.model;

import com.example.instagram.instagram.common.FollowStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "follows")
@Table(name = "follows")
public class Follows extends BaseEntity {

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid", nullable = false, referencedColumnName = "uuid")
    private User user; // user who is following

    // @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_uuid", nullable = false, referencedColumnName = "uuid")
    private User target_user; // user who is being followed

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FollowStatus status;

    public Follows() {
    }

    public Follows(User user, User target_user, FollowStatus status) {
        this.user = user;
        this.target_user = target_user;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getTarget_user() {
        return target_user;
    }

    public void setTarget_user(User target_user) {
        this.target_user = target_user;
    }

    public FollowStatus getStatus() {
        return status;
    }

    public void setStatus(FollowStatus status) {
        this.status = status;
    }
}
