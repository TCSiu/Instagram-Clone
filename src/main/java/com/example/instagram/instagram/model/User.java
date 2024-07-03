package com.example.instagram.instagram.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity implements CustomUserDetails {
    @Column(name = "USERNAME")
    private String loginUsername;
    @Column(name = "EMAIL")
    private String email;
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private Set<Follows> followers;
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private Set<Follows> followings;
    @OneToOne(cascade = CascadeType.ALL)
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

    @Override
    public String getUsername() {
        return loginUsername;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return CustomUserDetails.super.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
