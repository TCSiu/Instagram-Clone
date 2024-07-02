package com.example.instagram.instagram.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import com.example.instagram.instagram.common.StorageProperty;
import com.example.instagram.instagram.exception.StorageFileNotFoundException;
import com.example.instagram.instagram.model.Media;
import com.example.instagram.instagram.model.Post;
import com.example.instagram.instagram.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.instagram.instagram.exception.StorageException;
import com.example.instagram.instagram.exception.StorageFileEmptyException;
import com.example.instagram.instagram.exception.StorageFilePathWrongException;
import com.example.instagram.instagram.service.MediaService;

@Service
public class MediaImpl implements MediaService {

    private final Path storageFolderPath;

    @Autowired
    private MediaRepository mediaRepository;

    public MediaImpl(StorageProperty storageProperty) {
        if (storageProperty.getPath().trim().isEmpty()) {
            throw new StorageException("File upload location can not be Empty.");
        }
        this.storageFolderPath = Paths.get(storageProperty.getPath());
    }

    @Override
    public void init() {
        try {
			Files.createDirectories(storageFolderPath);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
    }

    @Override
    public Media store(MultipartFile file, Post post) throws StorageFileEmptyException, StorageFilePathWrongException {
        try {
            if (file.isEmpty()) {
                throw new StorageFileEmptyException("Failed to store empty file");
            }
            String filename = file.getOriginalFilename();
            String extension = StringUtils.getFilenameExtension(filename);
            String newFileName = UUID.randomUUID().toString() + "." + extension;

            Path destinationFile = this.storageFolderPath.resolve(Paths.get(newFileName)).normalize().toAbsolutePath();
    
            if (!destinationFile.getParent().equals(this.storageFolderPath.toAbsolutePath())) {
                throw new StorageFilePathWrongException("Cannot store file outside current directory");
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return mediaRepository.saveImage(extension, newFileName, post);
        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Path load(String filename) {
        return storageFolderPath.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    
}
