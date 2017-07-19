package com.client;
/**
 * @author ZhangShaowei on 2017/6/13 14:43
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Feign interface
 *
 * @author ZhangShaowei on 2017/6/13 14:43
 **/
@FeignClient(value = "${file.upload.serverName}")
public interface FileUploadClinet {


    /**
     * @param files
     * @param path
     * @return
     */
    @RequestMapping(
            value = "file/batch/upload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<String> bacthUpload(@RequestPart("file") MultipartFile[] files, @RequestParam("path") String path);

    /**
     * @param file
     * @param path
     * @return
     */
    @RequestMapping(
            value = "file/single/upload",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String singleUpload(@RequestPart("file") MultipartFile file, @RequestParam("path") String path);

}
