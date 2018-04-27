package com.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


/**
 * @author ZhangShaowei on 2017/6/14 14:08
 */
@Service("fileUpDownService")
public class FileUpDownService {

    /**
     * 单文件上传
     *
     * @param file MultipartFile
     * @param path 目录地址
     * @return 文件名
     */
    public String singleUpload(final MultipartFile file, final String path) throws IOException {
        return this.upload(file, path);
    }


    /**
     * 上传
     *
     * @param file MultipartFile
     * @param path path
     * @return filename
     * @throws IOException e
     */
    private String upload(final MultipartFile file, final String path) throws IOException {
        StringBuilder builder = new StringBuilder(50);
        builder.append(UUID.randomUUID())
                .append(".").append(StringUtils.getFilenameExtension(file.getOriginalFilename()));

        Path dir = Paths.get(path);
        Files.createDirectories(dir);
        Files.copy(
                file.getInputStream(),
                Paths.get(path + file.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING
        );
//        file.transferTo(new File(dir.toString(), builder.toString()));
        return builder.toString();
    }

}
