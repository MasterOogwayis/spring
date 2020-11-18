package com.zsw.test.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@RestController
@RequestMapping("/test")
public class HelloWorldApi {

    @Value("${random.int[9000,10000]}")
    private String random;

    @GetMapping("hello")
    public String world(@RequestParam("name") String name) {
        return "Hello " + name + random;
    }

    @GetMapping("download")
    public FileSystemResource download1() {
        return new FileSystemResource(Paths.get("/data/1.txt"));
    }

    @GetMapping("download")
    public ResponseEntity<FileSystemResource> download() {
        FileSystemResource resource = new FileSystemResource(Paths.get("/data/1.txt"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", resource.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.getFile().length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }


}
