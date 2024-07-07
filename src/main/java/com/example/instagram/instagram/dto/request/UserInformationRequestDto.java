package com.example.instagram.instagram.dto.request;

import com.example.instagram.instagram.dto.BaseDto;

public class UserInformationRequestDto extends BaseDto {
    private String first_name;
    private String last_name;
    private Integer age;
    private String gender;

    public UserInformationRequestDto() {
    }

    public UserInformationRequestDto(String first_name, String last_name, Integer age, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
