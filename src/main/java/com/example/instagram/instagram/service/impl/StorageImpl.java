package com.example.instagram.instagram.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.common.StorageProperties;
import com.example.instagram.instagram.exception.StorageException;
import com.example.instagram.instagram.exception.StorageFileEmptyException;
import com.example.instagram.instagram.exception.StorageFilePathWrongException;
import com.example.instagram.instagram.service.StorageService;

@Service
public class StorageImpl implements StorageService {

    @Autowired
    private StorageProperties storageProperties;

    private final Path storagePath;

    public StorageImpl() {
        if(storageProperties.getStoragePath().trim().length() == 0){
            throw new StorageException("File upload location can not be Empty."); 
        }
        this.storagePath = Paths.get(storageProperties.getStoragePath());
    }

    @Override
    public void init() {
        try {
			Files.createDirectories(Paths.get(storageProperties.getStoragePath()));
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
    }

    @Override
    public void store(MultipartFile file) throws StorageFileEmptyException, StorageFilePathWrongException {
        try {
            if (file.isEmpty()) {
                throw new StorageFileEmptyException("Failed to store empty file");
            }
            String filename = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(filename);
            String newFileName = UUID.randomUUID().toString() + "." + extension;
            Path destinationFile = this.storagePath.resolve(Paths.get(newFileName)).normalize().toAbsolutePath();
    
            if (!destinationFile.getParent().equals(this.storagePath.toAbsolutePath())) {
                throw new StorageFilePathWrongException("Cannot store file outside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }


    }

    @Override
    public Path load(String filename) {
        return storagePath.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        }
    }
    
}
