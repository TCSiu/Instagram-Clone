package com.example.instagram.instagram.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "media")
public class Media extends BaseEntity {
    @Column(name = "media_url")
    private String mediaUrl;
    @Column(name = "media_type")
    private String mediaType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_uuid", referencedColumnName = "uuid")
    private Post post;
    @Column(name = "status")
    private Boolean status;

    public Media() {
    }

    public Media(String mediaUrl, String mediaType) {
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.status = true;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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
