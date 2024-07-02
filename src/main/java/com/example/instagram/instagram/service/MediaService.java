package com.example.instagram.instagram.service;

import java.nio.file.Path;

import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    void init();
    Media store(MultipartFile file, Post post);
    // Stream<Path> loadAll();
    Path load(String filename);
    Resource loadAsResource(String filename);
    // void deleteAll();
}
