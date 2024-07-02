package com.example.instagram.instagram.Dto;


import jakarta.validation.constraints.Size;

public class PostDto extends BaseDto {
    @Size(max = 500, message = "Caption must be less than 500 characters")
    private String caption;
    @Size(max = 100, message = "Location must be less than 100 characters")
    private String location;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
