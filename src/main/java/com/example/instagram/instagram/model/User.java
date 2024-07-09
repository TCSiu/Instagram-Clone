package com.example.instagram.instagram.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
@JsonFilter("userFilter")
public class User extends BaseEntity {

    @Column(name = "USERNAME")
    private String loginUsername;

    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "targetUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Follows> followers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Follows> followings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_information_uuid", referencedColumnName = "uuid")
    private UserInformation userInformation;

    public User() {
    }

    public User(String loginUsername, String email, String password) {
        this.loginUsername = loginUsername;
        this.email = email;
        this.password = password;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Follows> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<Follows> followers) {
        this.followers = followers;
    }

    public Set<Follows> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<Follows> followings) {
        this.followings = followings;
    }

    public UserInformation getUserInformation() { return userInformation; }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", uuid='" + getUuid() + '\'' +
                ", login_username='" + getLoginUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", created_at='" + getCreatedAt() + '\'' +
                ", created_by='" + getCreatedBy() + '\'' +
                ", updated_at='" + getUpdatedAt() + '\'' +
                ", updated_by='" + getUpdatedBy() + '\'' +
                '}';
    }
}
