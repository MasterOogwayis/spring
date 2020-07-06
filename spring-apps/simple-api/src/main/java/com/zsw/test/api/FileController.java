package com.zsw.test.api;

import lombok.SneakyThrows;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZhangShaowei on 2020/6/2 16:00
 */
@RequestMapping("file")
@RestController
public class FileController {

    @SneakyThrows
    @PostMapping("upload")
    public Object upload(@RequestPart MultipartFile file) {
        StringBuilder builder = new StringBuilder(50);
        builder.append(UUID.randomUUID())
                .append(".").append(StringUtils.getFilenameExtension(file.getOriginalFilename()));
        String fileName = builder.toString();
        String path = "/data/file";
        Files.createDirectories(Paths.get(path));
        Files.copy(
                file.getInputStream(),
                Paths.get(path, fileName),
                StandardCopyOption.REPLACE_EXISTING
        );
        return fileName;
    }


    @SneakyThrows
    @PostMapping("save")
    public Object save(HttpServletRequest request, @RequestParam Map<String, Object> map) {
        StringBuilder builder = new StringBuilder(50);
        builder.append(UUID.randomUUID())
                .append(".").append("txt");
        String fileName = builder.toString();
        String path = "/data/file";
        Files.createDirectories(Paths.get(path));
        Files.copy(
                request.getInputStream(),
                Paths.get(path, fileName),
                StandardCopyOption.REPLACE_EXISTING
        );
        return fileName;
    }


}
