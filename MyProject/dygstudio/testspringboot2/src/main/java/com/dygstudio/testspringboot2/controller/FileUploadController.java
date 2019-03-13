package com.dygstudio.testspringboot2.controller;

import com.dygstudio.testspringboot2.config.StorageFileNotFoundException;
import com.dygstudio.testspringboot2.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @Project: dygstudio
 * @Author: diyaguang
 * @CreateDate: 2019/3/13-15:31
 * @Description:
 */
@Controller
public class FileUploadController {
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException{
        model.addAttribute("files",storageService.loadAll()
                        // 解析这一行看起来复杂的代码
                        // Stream.map 将一个 Stream 使用给定的转换函数 （Lambda） 映射为一个新的 Stream
                        // 参数 path 即为 Stream<Path> 中的 Path
                        // fromMethodName 方法创建一个 UriComponentsBuilder 对象 (通过 controller 方法名上的 mapping 路径与参数组数)
                        // 返回的 UriComponentsBuilder 的 build 方法 创建一个 UriComponents 对象, 最后将 UriComponents 转换为字符串
                        // Stream.collect 方法将 map 转换后的新 Stream 变为 list 返回给模板
                        // 这一系列操作，实际上就是为了把 Stream<Path> 转换为一个存储 url 字符串的列表对象
                .map(path-> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"serveFile",path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        return "uploadForm";
    }

    // 文件下载
    // 正则表达式匹配, 语法: {varName:regex} 前面式变量名，后面式表达式
    // 匹配出现过一次或多次.的字符串 如: "xyz.png"
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        // 根据文件名读取文件
        Resource file = storageService.loadAsResource(filename);
        // @ResponseBody 用于直接返回结果(自动装配)
        // ResponseEntity 可以定义返回的 HttpHeaders 和 HttpStatus (手动装配)
        // ResponseEntity.ok 相当于设置 HttpStatus.OK (200)
        // CONTENT_DISPOSITION 该 标志将通知浏览器启动下载
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    // 处理上传逻辑
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        // 保存文件
        storageService.store(file);

        // 使用 RedirectAttributes 添加一个重定向参数
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/upload";
    }

    // 统一处理该 controller 异常
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
