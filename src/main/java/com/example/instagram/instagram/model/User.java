package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements UserDetails {
    @Column(name = "USERNAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() { return email; }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = name;
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
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", uuid='" + getUuid() + '\'' +
                ", username='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", created_at='" + getCreatedAt() + '\'' +
                ", created_by='" + getCreatedBy() + '\'' +
                ", updated_at='" + getUpdatedAt() + '\'' +
                ", updated_by='" + getUpdatedBy() + '\'' +
                '}';
    }
}
