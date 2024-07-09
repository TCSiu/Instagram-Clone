package com.example.instagram.instagram.service;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.example.instagram.instagram.dto.request.UserInformationRequestDto;
import com.example.instagram.instagram.model.User;

public interface UserService {
    List<User> allUsers();

    MappingJacksonValue getUserByUuid(String userUuid);

    MappingJacksonValue editUserInformation(String userUuid, UserInformationRequestDto userInformationDto);
}
