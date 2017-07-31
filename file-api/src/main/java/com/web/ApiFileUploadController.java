package com.web;

import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * @author ZhangShaowei on 2017/6/12 14:23
 */
@RestController
@RequestMapping("file")
public class ApiFileUploadController {


    /**
     * @param files 文件
     * @return
     */
    @PostMapping("batch/upload")
    @Deprecated
    public List<String> bacthUpload(@RequestPart("files") MultipartFile[] files, @RequestParam("path") String path) {
        String fileName = null;
        List<String> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(files)) {

            try {
                for (MultipartFile file : files) {
                    fileName = file.getOriginalFilename();
                    file.transferTo(new File(path + fileName));
                    list.add(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        } else {
            return Arrays.asList("faild for not found!");
        }
    }


    /**
     * @param file
     * @return
     */
    @PostMapping(value = "single/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String singleUpload(@RequestPart("file") MultipartFile file, @RequestParam("path") String path) {
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
                e.printStackTrace();
                return e.getMessage();
            }
            return "success:" + file.getOriginalFilename() ;
        }
        return "file not found!";

    }


    /**
     * @param path path
     * @param filename filename
     * @return InputStreamResource
     * @throws IOException e
     */
    @PostMapping(value = "download")
    public ResponseEntity<byte[]> download(
            @RequestParam("path") final String path,
            @RequestParam("filename") final String filename) throws IOException{
        FileSystemResource file = new FileSystemResource(new File(path, filename));
        try (InputStream in = file.getInputStream()){
//            Map<String, Collection<String>> headers = new HashMap<>();
//            headers.put(HttpHeaders.CACHE_CONTROL, Arrays.asList("no-cache, no-store, must-revalidate"));
//            headers.put(
//                    HttpHeaders.CONTENT_DISPOSITION, Arrays.asList(String.format("attachment; filename=\"%s\"", filename)));
//            headers.put(HttpHeaders.PRAGMA, Arrays.asList("no-cache"));
//            headers.put(HttpHeaders.EXPIRES, Arrays.asList("0"));
//
//            return Response.builder().status(200).reason("success").headers(headers).body(IOUtils.toByteArray(in)).build();
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
