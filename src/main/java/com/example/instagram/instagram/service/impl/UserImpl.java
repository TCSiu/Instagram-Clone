package com.example.instagram.instagram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.example.instagram.instagram.dto.request.UserInformationRequestDto;
import com.example.instagram.instagram.exception.UserNoFoundException;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.FilterBeanService;
import com.example.instagram.instagram.service.UserInformationService;
import com.example.instagram.instagram.service.UserService;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final UserInformationService userInformationService;

    public UserImpl(UserRepository userRepository, UserInformationService userInformationService) {
        this.userRepository = userRepository;
        this.userInformationService = userInformationService;
    }

    @Override
    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public MappingJacksonValue getUserByUuid(String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found with UUID: " + userUuid));
        return getMappingUser(user, "User retrieved successfully");
    }

    
    @Override
    public MappingJacksonValue editUserInformation(String userUuid, UserInformationRequestDto userInformationDto) {
        User user = userRepository.findByUuid(userUuid).orElseThrow(() -> new UserNoFoundException("User not found with UUID: " + userUuid));
        userInformationService.editUserInformation(user.getUuid(), userInformationDto);
        return getMappingUserWithUserInformation(user, "User information updated successfully");
    }

    protected MappingJacksonValue getMappingUser(User user, String message) {
        String[] userFilterFields = { "followers", "followings", "userInformation" };
        SimpleFilterProvider filterProvider = FilterBeanService.createFilterProvider();
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "userFilter", userFilterFields);
        MappingJacksonValue mapping = FilterBeanService.getFilterdValue(filterProvider, user, message);
        return mapping;
    }

    protected MappingJacksonValue getMappingUserWithUserInformation(User user, String message) {
        String[] userFilterFields = { "followers", "followings" };
        SimpleFilterProvider filterProvider = FilterBeanService.createFilterProvider();
        filterProvider = FilterBeanService.addEntityFilter(filterProvider, "userFilter", userFilterFields);
        MappingJacksonValue mapping = FilterBeanService.getFilterdValue(filterProvider, user, message);
        return mapping;
    }
}
