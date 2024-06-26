package com.example.instagram.instagram.exception;

public class StorageFileEmptyException extends StorageException {

    public StorageFileEmptyException(String msg) {
        super(msg);
    }

    public StorageFileEmptyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
