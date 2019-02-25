package com.dygstudio.testspringboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/2/25-9:27
 * @Description:
 */
@Configuration
@Component
@ConfigurationProperties("web")
public class CustomWebConfig {
    private String name;
    private String version;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
