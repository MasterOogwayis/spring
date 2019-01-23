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
import org.springframework.web.bind.annotation.*;
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
                    Paths.get(path, file.getOriginalFilename()),
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
            @RequestPart("file") final MultipartFile file, final @RequestParam("path") String path) {
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
     * Springboot对资源的描述提供了相应的接口，其主要实现类有ClassPathResource、FileSystemResource、UrlResource、ByteArrayResource、
     * ServletContextResource和InputStreamResource。
     * 1. ClassPathResource 可用来获取类路径下的资源文件。假设我们有一个资源文件test.txt在类路径下，我们就可以通过给定对应资源文件在类路径下的路径path来获取它，new ClassPathResource(“test.txt”)。
     * 2. FileSystemResource 可用来获取文件系统里面的资源。我们可以通过对应资源文件的文件路径来构建一个FileSystemResource。FileSystemResource还可以往对应的资源文件里面写内容，当然前提是当前资源文件是可写的，这可以通过其isWritable()方法来判断。FileSystemResource对外开放了对应资源文件的输出流，可以通过getOutputStream()方法获取到。
     * 3. UrlResource 可用来代表URL对应的资源，它对URL做了一个简单的封装。通过给定一个URL地址，我们就能构建一个UrlResource。
     * 4. ByteArrayResource 是针对于字节数组封装的资源，它的构建需要一个字节数组。
     * 5. ServletContextResource 是针对于ServletContext封装的资源，用于访问ServletContext环境下的资源。ServletContextResource持有一个ServletContext的引用，其底层是通过ServletContext的getResource()方法和getResourceAsStream()方法来获取资源的。
     * 6. InputStreamResource 是针对于输入流封装的资源，它的构建需要一个输入流。
     *
     * @return
     * @throws Exception
     */
    @GetMapping
    public ResponseEntity<FileSystemResource> download() throws Exception {
        String path = "C:\\Users\\ZhangShaowei\\Desktop";
        String fileName = "1.txt";

        HttpHeaders headers = new HttpHeaders();
        FileSystemResource fileSystemResource = new FileSystemResource(Paths.get(path, fileName).toString());
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + ".pdf\"");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(fileSystemResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileSystemResource);
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
