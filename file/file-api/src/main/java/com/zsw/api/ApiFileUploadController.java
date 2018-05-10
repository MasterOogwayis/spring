package com.zsw.api;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
@RequestMapping("file")
public class ApiFileUploadController {
    
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @param files 文件
     * @param path  保存路径
     * @return List<String>
     * @deprecated deprecated
     */
    @PostMapping("batch/upload")
    @Deprecated
    public List<String> bacthUpload(
            @RequestPart("file") final MultipartFile[] files, final @RequestParam("path") String path) {
//        String fileName = null;
        List<String> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(files)) {

            try {
                for (MultipartFile file : files) {
//                    fileName = file.getOriginalFilename();
//                    file.transferTo(new File(path + fileName));
                    logger.info("上传文件: {}", file.getOriginalFilename());
                    list.add(this.upload(file, path));
                }
            } catch (Exception e) {
//                e.printStackTrace();
                this.logger.error("文件上传失败: {}", e.getMessage());
            }
            return list;
        } else {
            return Collections.singletonList("faild for not found!");
        }
    }


    /**
     * 上传
     *
     * @param file MultipartFile
     * @param path path
     * @return filename
     * @throws Exception e
     */
    private String upload(final MultipartFile file, final String path) throws Exception {
        if (!file.isEmpty()) {
            StringBuilder builder = new StringBuilder(50);
            builder.append(UUID.randomUUID())
                    .append(".").append(StringUtils.getFilenameExtension(file.getOriginalFilename()));
            Files.createDirectories(Paths.get(path));
            Files.copy(
                    file.getInputStream(),
                    Paths.get(path + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );
//            file.transferTo(new File(path, builder.toString()));
            return builder.toString();
        }
        return null;
    }

    /**
     * 单文件上传
     *
     * @param file MultipartFile
     * @param path path
     * @return String
     */
    @PostMapping(value = "single/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleUpload(
            @RequestPart("file") final MultipartFile file, final  @RequestParam("path") String path) {
        if (file != null && !file.isEmpty()) {
//            try (
//                    BufferedOutputStream out = new BufferedOutputStream(
//                            new FileOutputStream(new File(PATH + file.getOriginalFilename())));
//            ) {
//                out.write(file.getBytes());
//            } catch (Exception e) {
//                e.printStackTrace();
//                return e.getMessage();
//            }
            try {
                Files.createDirectories(Paths.get(path));
//                Files.copy(
//                        file.getInputStream(),
//                        Paths.get(path + file.getOriginalFilename()),
//                        StandardCopyOption.REPLACE_EXISTING);
                file.transferTo(new File(path, file.getOriginalFilename()));
            } catch (Exception e) {
//                e.printStackTrace();
                return e.getMessage();
            }
            return "success:" + file.getOriginalFilename();
        }
        return "file not found!";

    }


    /**
     * 文件下载
     *
     * @param path     path
     * @param filename filename
     * @return InputStreamResource
     * @throws IOException e
     */
    @PostMapping(value = "download")
    public ResponseEntity<byte[]> download(
            @RequestParam("path") final String path,
            @RequestParam("filename") final String filename) throws IOException {
        FileSystemResource file = new FileSystemResource(new File(path, filename));
        try (InputStream in = file.getInputStream()) {
//            Map<String, Collection<String>> headers = new HashMap<>();
//            headers.put(HttpHeaders.CACHE_CONTROL, Arrays.asList("no-cache, no-store, must-revalidate"));
//            headers.put(
//                    HttpHeaders.CONTENT_DISPOSITION,
//                      Arrays.asList(String.format("attachment; filename=\"%s\"", filename)));
//            headers.put(HttpHeaders.PRAGMA, Arrays.asList("no-cache"));
//            headers.put(HttpHeaders.EXPIRES, Arrays.asList("0"));
//
//            return Response.builder().status(200).reason("success")
//                      .headers(headers).body(IOUtils.toByteArray(in)).build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(
                    HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", file.getFilename()));
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(IOUtils.toByteArray(in));
        }
    }


    /**
     * @return MultipartConfigElement
     */
    @Bean
    MultipartConfigElement createMultipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        /**
         * 设置最大上传文件的大小，默认是1MB
         */
        mcf.setMaxFileSize("50MB");
        return mcf.createMultipartConfig();
    }

}
