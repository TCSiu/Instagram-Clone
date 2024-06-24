package com.example.instagram.instagram.model;

import com.example.instagram.instagram.Dto.UserInformationDto;
import com.example.instagram.instagram.common.Gender;
import jakarta.persistence.*;
import org.apache.commons.lang3.EnumUtils;

@Entity(name = "user_information")
@Table(name = "user_information")
public class UserInformation extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne(mappedBy = "userInformation")
    private User user;

    public UserInformation() {
    }

    public UserInformation(String firstName, String lastName, Integer age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void update(UserInformationDto userInformationDto) {
        if (userInformationDto.getFirst_name() != null && ! userInformationDto.getFirst_name().isEmpty()) this.firstName = userInformationDto.getFirst_name();
        if (userInformationDto.getLast_name() != null && ! userInformationDto.getLast_name().isEmpty()) this.lastName = userInformationDto.getLast_name();
        if (userInformationDto.getAge() != null && userInformationDto.getAge() > 0) this.age = userInformationDto.getAge();
        if (userInformationDto.getGender() != null && ! userInformationDto.getGender().isEmpty()) this.gender = EnumUtils.getEnum(Gender.class, userInformationDto.getGender().toUpperCase());
    }
}
