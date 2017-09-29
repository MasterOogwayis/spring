package com.zsw.client;
/**
 * @author ZhangShaowei on 2017/6/13 14:43
 */

import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Feign interface
 *
 * @author ZhangShaowei on 2017/6/13 14:43
 **/
@FeignClient(value = "${zsw.base.serverName.file}")
public interface FileUploadClinet {


    /**
     * @param files files
     * @param path  path
     * @return names
     */
    @RequestMapping(
            value = "file/batch/upload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<String> bacthUpload(@RequestPart("file") MultipartFile[] files, @RequestParam("path") String path);

    /**
     * @param file file
     * @param path path
     * @return name
     */
    @RequestMapping(
            value = "file/single/upload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String singleUpload(@RequestPart("file") MultipartFile file, @RequestParam("path") String path);


    /**
     * byte[]  it may cause OutOfMemoryError,
     *
     * @param path     path
     * @param filename filename
     * @return InputStreamResource
     */
    @RequestMapping(value = "file/download", method = RequestMethod.POST)
    feign.Response download(
            @RequestParam("path") String path,
            @RequestParam("filename") String filename);

}
