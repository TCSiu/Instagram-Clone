package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements CustomUserDetails {
    @Column(name = "USERNAME")
    private String loginUsername;
    @Column(name = "EMAIL")
    private String email;
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Follows> followers = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Follows> followings = new HashSet<>();

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

    public Object getFollowerList() {
        List<User> temp = new ArrayList<>();
        for (Follows f: followings) {
            temp.add(f.getFollowing());
        }
        return temp;
//        return followings;
    }

    public static User extractFollowing(Follows follows) {
        return follows.getFollower();
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
