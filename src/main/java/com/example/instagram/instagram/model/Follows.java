package com.example.instagram.instagram.model;

import com.example.instagram.instagram.common.FollowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "follows")
@Table(name = "follows")
public class Follows extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "following_uuid", nullable = false, referencedColumnName = "uuid")
    private User following;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "follower_uuid", nullable = false, referencedColumnName = "uuid")
    private User follower;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FollowStatus status;

    public Follows() {
    }

    public Follows(User following, User follower, FollowStatus status) {
        this.following = following;
        this.follower = follower;
        this.status = status;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public FollowStatus getStatus() {
        return status;
    }

    public void setStatus(FollowStatus status) {
        this.status = status;
    }
}
