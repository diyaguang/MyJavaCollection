package com.dygstudio.testspringboot2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-15:43
 * @Description:
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    private String location="upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
