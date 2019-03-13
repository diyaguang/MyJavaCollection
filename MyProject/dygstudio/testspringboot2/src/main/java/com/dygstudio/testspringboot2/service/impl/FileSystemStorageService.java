package com.dygstudio.testspringboot2.service.impl;

import com.dygstudio.testspringboot2.config.StorageException;
import com.dygstudio.testspringboot2.config.StorageFileNotFoundException;
import com.dygstudio.testspringboot2.config.StorageProperties;
import com.dygstudio.testspringboot2.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-15:39
 * @Description:
 */
@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file "+filename);
            }
            if(filename.contains("..")){
                throw new StorageException("Cannot store file with relative path outside current directory "+filename);
            }
            Files.copy(file.getInputStream(),this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new StorageException("Failed to store file "+filename,e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            // 通过给定的目录和深度来遍历，返回 Stream (集合中包含给定的路径)
            // filter 过滤掉指定的路径
            // map 将路径处理为相对路径，如: rootLocation = "a/b" path = "a/b/c/img.png" relativize 后，结果为 "c/img.png"
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        // 组合一个新的 Path 对象, 如: filename = "gus" rootLocation="a/b", 执行后结果为 "a/b/gus"
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            // file.toUri 将 Path 转换为 uri
            // 如: path = "upload-dir/1.jpg" toUrl 后结果为 "file:///home/maiyo/project/upload-files/upload-dir/1.jpg"
            // 通过 UrlResource 创建一个 Srping Resource 对象
            Resource resource = new UrlResource(file.toUri());

            // 判断资源是否存在与可读
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        // 删除该目录下所有文件
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            // 创建上传目录
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
