package com.example.instagram.instagram.service;

import com.example.instagram.instagram.Dto.UserInformationDto;
import com.example.instagram.instagram.model.UserInformation;

public interface UserInformationService {
    void EditUserInformation(String currentUserUuid, UserInformationDto userInformationDto);
}
