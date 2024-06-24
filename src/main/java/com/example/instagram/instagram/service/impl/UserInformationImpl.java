package com.example.instagram.instagram.service.impl;

import com.example.instagram.instagram.Dto.UserInformationDto;
import com.example.instagram.instagram.model.User;
import com.example.instagram.instagram.model.UserInformation;
import com.example.instagram.instagram.repository.UserInformationRepository;
import com.example.instagram.instagram.repository.UserRepository;
import com.example.instagram.instagram.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInformationImpl implements UserInformationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Override
    public void editUserInformation(String currentUserUuid, UserInformationDto userInformationDto) {
        User currentUser = userRepository.findByUuid(currentUserUuid)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        UserInformation userInformation = currentUser.getUserInformation();
        userInformation.update(userInformationDto);
        userInformationRepository.save(userInformation);
    }
}
