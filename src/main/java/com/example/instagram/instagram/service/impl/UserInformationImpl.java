package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.Dto.UserInformationDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.model.UserInformation;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserInformationImpl implements UserInformationService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void EditUserInformation(String currentUserUuid, UserInformationDto userInformationDto) {
        User currentUser = userRepository.findByUuid(currentUserUuid)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return ;
    }
}
