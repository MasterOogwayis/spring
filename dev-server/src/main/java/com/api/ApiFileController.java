package com.api;

import com.service.FileUpDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZhangShaowei on 2017/6/14 13:45
 */
@RestController
@RequestMapping("file")
public class ApiFileController {

    /** */
    @Autowired
    private FileUpDownService fileUpDownService;

    /**
     * 单文件上传 -- 只接受文件上传请求
     *
     * @param request HttpServletRequest
     * @param file    MultipartFile
     * @param path    path 需要上传的路径
     * @return finame = uuid + . + type
     * @throws Exception
     */
    @PostMapping(
            value = "single/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String singleUpload(
            final HttpServletRequest request,
            @RequestPart("file") final MultipartFile file,
            @RequestParam("path") final String path) throws Exception {
        return this.fileUpDownService.singleUpload(file, request + path);
    }


    /**
     * 通过cloud下载必须使用byte[]   or    使用InputStream cause error : stream is close
     * 客户端使用 feign.Response 接收 ResponseEntity<byte[]> ，避免内存中加载太多byte[]数据导致 OOM
     *
     * @param path     文件路径
     * @param filename 文件名
     * @return byte[] data
     * @throws IOException e
     */
    @PostMapping(value = "download")
    public ResponseEntity<byte[]> download(
            @RequestParam("path") final String path,
            @RequestParam("filename") final String filename) throws IOException {
        FileSystemResource file = new FileSystemResource(new File(path, filename));
        try (InputStream in = file.getInputStream()) {
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
                    .body(org.apache.commons.io.IOUtils.toByteArray(in));
        }
    }

    /**
     * @param filename
     * @return
     * @throws IOException
     */
    @GetMapping("user/image")
    public ResponseEntity<InputStreamResource> image(
            @RequestParam("filename") final String filename) throws IOException {
        String path = "/data/lenosp/user/image";
        FileSystemResource file = new FileSystemResource(new File(path, filename));
        InputStream in = file.getInputStream();
            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            headers.add(
                    HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
//            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(file.contentLength())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(in));
        }


}
