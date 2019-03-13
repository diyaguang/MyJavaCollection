package com.dygstudio.testspringboot2.config;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-15:57
 * @Description:
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
