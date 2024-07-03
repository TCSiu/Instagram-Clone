package com.example.instagram.instagram.service;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;

public interface MediaService {
    void init();
    Media store(MultipartFile file, Post post);
    Path load(String filename);
    Resource loadAsResource(String filename);
}
