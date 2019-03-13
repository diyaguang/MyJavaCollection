package com.dygstudio.testspringboot2.config;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-16:02
 * @Description:
 */
public class StorageFileNotFoundException extends StorageException {
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
