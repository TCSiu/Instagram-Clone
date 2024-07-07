package com.example.instagram.instagram.service;

import com.example.instagram.instagram.dto.request.UserInformationRequestDto;

public interface UserInformationService {
    void editUserInformation(String currentUserUuid, UserInformationRequestDto userInformationDto);
}
