package com.example.instagram.instagram.dto;

public class FollowEntityDto extends BaseEntityDto {
    private String user_uuid;

    private String target_user_uuid;

    private String status;

    public FollowEntityDto() {
    }

    public FollowEntityDto(Long id, String uuid, String user_uuid, String target_user_uuid, String status, Long createdAt, String createdBy, Long updatedAt, String updatedBy) {
        super(id, target_user_uuid, createdAt, createdBy, updatedAt, updatedBy);
        this.user_uuid = user_uuid;
        this.target_user_uuid = target_user_uuid;
        this.status = status;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }

    public String getTarget_user_uuid() {
        return target_user_uuid;
    }

    public void setTarget_user_uuid(String target_user_uuid) {
        this.target_user_uuid = target_user_uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
