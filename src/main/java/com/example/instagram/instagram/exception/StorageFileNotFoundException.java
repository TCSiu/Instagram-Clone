package com.example.instagram.instagram.exception;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public StorageFileNotFoundException(String msg) {
        super(msg);
    }
    
}
