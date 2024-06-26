package com.example.instagram.instagram.controller;

import com.example.instagram.instagram.service.StorageService;
import com.example.instagram.instagram.service.impl.StorageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private StorageService storageService;
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserUuid = (String) authentication.getPrincipal();

        storageService.store(file);
        return file.getOriginalFilename();
    }
}
