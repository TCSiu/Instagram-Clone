package com.example.instagram.instagram.exception;

public class StorageFilePathWrongException extends StorageException {

    public StorageFilePathWrongException(String msg) {
        super(msg);
    }

    public StorageFilePathWrongException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
