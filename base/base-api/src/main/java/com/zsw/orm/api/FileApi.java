package com.zsw.orm.api;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

/**
 * 下载文件的几种方式
 *
 * @author ZhangShaowei on 2021/5/20 10:22
 */
@RestController
public class FileApi {


    /**
     * @return
     * @throws IOException
     */
    @GetMapping("download1")
    public ResponseEntity<FileSystemResource> download1() throws IOException {
        FileSystemResource resource = new FileSystemResource(Paths.get("/data/1.txt"));
        if (!resource.exists()) {
            throw new RuntimeException("file not found!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", resource.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


    /**
     * @return
     * @throws IOException
     */
    @GetMapping("download2")
    public ResponseEntity<StreamingResponseBody> download2() throws IOException {
        FileSystemResource resource = new FileSystemResource(Paths.get("/data/1.txt"));
        if (!resource.exists()) {
            throw new RuntimeException("file not found!");
        }
        InputStream inputStream = resource.getInputStream();
        StreamingResponseBody responseBody = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                int numberOfBytesToWrite;
                byte[] data = new byte[1024];
                while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, numberOfBytesToWrite);
                }
                inputStream.close();
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", resource.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(responseBody);
    }


}
