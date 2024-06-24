package com.example.instagram.instagram.service;

import com.example.instagram.instagram.Dto.UserInformationDto;

public interface UserInformationService {
    void editUserInformation(String currentUserUuid, UserInformationDto userInformationDto);
}
